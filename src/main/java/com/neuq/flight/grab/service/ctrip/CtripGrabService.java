package com.neuq.flight.grab.service.ctrip;

import com.google.common.collect.Lists;
import com.neuq.flight.grab.constant.enumType.TripType;
import com.neuq.flight.grab.constant.enumType.WebDriverType;
import com.neuq.flight.grab.entity.common.PriceResult;
import com.neuq.flight.grab.entity.common.PriceRouting;
import com.neuq.flight.grab.entity.common.PriceSegment;
import com.neuq.flight.grab.service.BaseDataService;
import com.neuq.flight.grab.service.WebDriverInvokeService;
import com.neuq.flight.grab.utils.builders.CtripUrlBuilder;
import com.neuq.flight.grab.utils.dom.DocumentUtil;
import com.neuq.flight.grab.utils.webdriver.WebDriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * Author: xiaoliufu
 * Date: 3/9/17
 */
@Service
@Slf4j
public class CtripGrabService {

    @Resource
    private WebDriverInvokeService webDriverInvokeService;
    @Resource
    private BaseDataService baseDataService;

    /**
     * 单程抓取数据
     *
     * @param fromCityCode 出发城市三字码
     * @param toCityCode   到达城市三字码
     * @param goDate       出发日期
     */
    public PriceResult getSimpleGrabSearchInfoOW(String fromCityCode, String toCityCode, String goDate) {
        //报价结果信息
        PriceResult priceResult = new PriceResult();
        //构建CtripUrlBuilder
        CtripUrlBuilder ctripUrlBuilder = CtripUrlBuilder.builder()
                .tripType(TripType.OW.getTripType())
                .baseDataService(baseDataService)
                .fromCityCode(fromCityCode)
                .toCityCode(toCityCode)
                .toDate(goDate)
                .retDate(null).build();
        //构建搜索URL
        String searchUrl = ctripUrlBuilder.getCtripUrl().orElse("");
        log.info("searchUrl={}", searchUrl);
        //获取HTML页面
        Optional<String> stringOptional = webDriverInvokeService.invoke(searchUrl, WebDriverType.CHROME);
        stringOptional.ifPresent(html -> {
            //获取航班列表集合信息
            Optional<Element> flightsListsOptional = getFlightsLists(html);
            if (!flightsListsOptional.isPresent()) {
                log.warn("getSimpleGrabSearchInfoOW no flightsLists!");
                return;
            }
            Element flightsList = flightsListsOptional.get();
            //获取航班列表信息
            Optional<Elements> flightItemsOptional = getFlightItems(flightsList);
            if (!flightItemsOptional.isPresent()) {
                log.warn("getSimpleGrabSearchInfoOW no flightItems!");
                return;
            }
            Elements flightItems = flightItemsOptional.get();
            //报价航线信息
            List<PriceRouting> priceRoutings = Lists.newArrayList();
            priceResult.setRoutings(priceRoutings);
            int index = 0;
            for (Element flightItem : flightItems) {
                //获取航班详情信息
                Optional<Element> flightDetailExpendOptional = getFlightDetailExpend(flightItem);
                if (!flightDetailExpendOptional.isPresent()) {
                    log.warn("getSimpleGrabSearchInfoOW no flightDetailExpend!");
                    return;
                }
                Element flightDetailExpend = flightDetailExpendOptional.get();
                //获取航班详情对应的航段信息
                Optional<Elements> flightDetailSectionsOptional = getFlightDetailSections(flightDetailExpend);
                if (!flightDetailSectionsOptional.isPresent()) {
                    log.warn("getSimpleGrabSearchInfoOW no flightDetailSections!");
                    return;
                }
                Elements flightDetailSections = flightDetailSectionsOptional.get();
                Elements sectionStops = null;
                Iterator<Element> eleStopIterator = null;
                //获取航班对应的经停信息
                Optional<Elements> sectionStopsOptional = getSectionStops(flightDetailExpend);
                if (!sectionStopsOptional.isPresent()) {
                    log.warn("getSimpleGrabSearchInfoOW no sectionStops");
                } else {
                    sectionStops = sectionStopsOptional.get();
                    //stop iterator
                    eleStopIterator = sectionStops.iterator();
                }
                //报价航线信息
                PriceRouting priceRouting = new PriceRouting();
                priceRoutings.add(priceRouting);
                //报价去程segment
                List<PriceSegment> fromSegments = Lists.newArrayList();
                priceRouting.setFromSegments(fromSegments);
                for (Element flightDetailSection : flightDetailSections) {
                    //航空公司名称
                    String carrier = getAirlineName(flightDetailSection);
                    //航班名称
                    String flightNo = getFlightNo(flightDetailSection);
                    //航班类型
                    String airCraft = getAircraft(flightDetailSection);
                    Optional<Elements> departAndArriveOptional = getDepartAndArrive(flightDetailSection);
                    if (!departAndArriveOptional.isPresent()) {
                        log.warn("getSimpleGrabSearchInfoOW no depart and arrive info");
                        return;
                    }
                    //出发和到达信息
                    Elements departAndArrive = departAndArriveOptional.get();
                    //出发信息
                    Element departEle = getDepartEle(departAndArrive);
                    //出发日期
                    Date depDate = getDate(departEle);
                    //出发机场三字码
                    String depAirportCode = getAirportCode(departEle);
                    //出发机场名称
                    String depAirportName = getAirportName(departEle);
                    //出发航站楼
                    String depTerminal = getAirportTerminal(departEle);
                    //飞行时长
                    String duration = getDuration(departEle);
                    StringBuilder stopCities = new StringBuilder();
                    //中转信息
                    if (eleStopIterator != null) {
                        while (eleStopIterator.hasNext()) {
                            //获取经停信息
                            Element sectionStop = eleStopIterator.next();
                            //经停城市中文名称
                            String cityCnName = getStopCity(sectionStop);
                            //经停时间
                            String stopTime = getStopTime(sectionStop);
                            stopCities.append(cityCnName);
                            stopCities.append("/");
                        }
                    }
                    if (stopCities.toString().contains("/")) {
                        stopCities.deleteCharAt(stopCities.lastIndexOf("/"));
                    }

                    //到达信息
                    Element arriveEle = getArriveEle(departAndArrive);
                    //到达日期
                    Date arrDate = getDate(arriveEle);
                    //到达机场三字码
                    String arrAirportCode = getAirportCode(arriveEle);
                    //到达机场名称
                    String arrAirportName = getAirportName(arriveEle);
                    //到达航站楼
                    String arrTerminal = getAirportTerminal(arriveEle);

                    //TODO 参数缺失
                    PriceSegment priceSegment = PriceSegment.builder()
                            .carrier(carrier)
                            .flightNumber(flightNo)
                            .depAirport(depAirportCode)
                            .depTime(depDate)
                            .arrAirport(arrAirportCode)
                            .arrTime(arrDate)
                            .stopCities(stopCities.toString())
                            .departureTerminal(depTerminal)
                            .arrivingTerminal(arrTerminal)
                            .aircraftCode(airCraft)
                            .build();
                    fromSegments.add(priceSegment);
                }
                List<Integer>prices =Lists.newArrayList();
                priceRouting.setPrices(prices);
                //获取代理商报价信息
                Optional<Elements> seatRowsOptional = getSeatsRow(flightItem);
                if (!seatRowsOptional.isPresent()) {
                    log.warn("getSimpleGrabSearchInfoOW no seatsRow!");
                } else {
                    Elements seatRowsEle = seatRowsOptional.get();
                    //遍历代理商报价信息
                    for (Element seatRow : seatRowsEle) {
                        //舱位等级
                        String seatType = getSeatType(seatRow);
                        String seatPrice = getSeatPrice(seatRow);
                        if (seatPrice!=null) {
                            prices.add(Integer.parseInt(seatPrice));
                        }

                    }
                }
            }

            //关闭webDriver
            WebDriverUtil.closeWebDriver();
        });
        return priceResult;
    }

    /**
     * 获取报价信息
     *
     * @param seatRow
     * @return
     */
    private String getSeatPrice(Element seatRow) {
        if (seatRow.is("div.seat-row")) {
            return seatRow.select("div.seat-price > div.mb5 > span.price").first().ownText();
        }
        return null;
    }

    private String getSeatType(Element seatRow) {
        Optional<Element> seatType = Optional.empty();
        if (seatRow.is("div.seat-row")) {
            Elements select = seatRow.select("div.seat-type");
            return select.first().ownText();
        }
        return null;
    }

    private Optional<Element> getFlightDetailExpend(Element flightItem) {
        Optional<Element> elementOptional = Optional.empty();
        if (flightItem.is("div.flight-item")) {
            Element first = flightItem.select("div.flight-detail-expend").first();
            elementOptional = Optional.ofNullable(first);
        }
        return elementOptional;
    }

    private Optional<Elements> getFlightItems(Element flightsList) {
        Optional<Elements> elementsOptional = Optional.empty();
        if (flightsList.is("#flightList")) {
            Elements select = flightsList.select("div.flight-item");
            elementsOptional = Optional.ofNullable(select);
        }
        return elementsOptional;
    }

    private Optional<Elements> getSeatsRow(Element flightItem) {
        Optional<Elements> elementOptional = Optional.empty();
        if (flightItem.is("div.flight-item")) {
            Elements select = flightItem.select("div.seats-list").first().select("div.seat-row");
            elementOptional = Optional.ofNullable(select);
        }
        return elementOptional;
    }

    /**
     * 获取航班列表信息
     *
     * @param html HTML
     * @return element
     */
    private Optional<Element> getFlightsLists(String html) {
        Optional<Element> elementOptional = Optional.empty();
        Document document = DocumentUtil.getDocument(html);
        if (document != null) {
            Element first = document.select("div#flightList").first();
            elementOptional = Optional.ofNullable(first);
        }
        return elementOptional;
    }


    /**
     * 获取flight-detail-expends
     *
     * @param flightItems flightItems
     * @return 指定类型的元素
     */
    private Elements getFlightDetailExpends(Elements flightItems) {
        if (flightItems.is("div#flightList")) {
            return flightItems.select("div.flight-item > div.flight-detail-expend");
        }
        return null;
    }


    /**
     * 获取flight-detail-sections
     *
     * @param flightDetailExpend flightDetailExpend
     * @return Elements
     */
    private Optional<Elements> getFlightDetailSections(Element flightDetailExpend) {
        Optional<Elements> elementsOptional = Optional.empty();
        if (flightDetailExpend.is("div.flight-detail-expend")) {
            Elements select = flightDetailExpend.select("div.flight-detail-section");
            elementsOptional = Optional.ofNullable(select);
        }
        return elementsOptional;
    }

    /**
     * 获取sction-stop
     *
     * @param flightDetailExpend flightDetailExpend
     * @return Elements
     */
    private Optional<Elements> getSectionStops(Element flightDetailExpend) {
        Optional<Elements> elementsOptional = Optional.empty();
        if (flightDetailExpend.is("div.flight-detail-expend")) {
            Elements select = flightDetailExpend.select("div.section-stop");
            elementsOptional = Optional.ofNullable(select);
        }
        return elementsOptional;
    }

    /**
     * 获取航班名称
     *
     * @param flightDetailSection
     * @return
     */
    private String getAirlineName(Element flightDetailSection) {
        if (flightDetailSection.is("div.flight-detail-section")) {
            Element sectionFlightBase = flightDetailSection.select("p.section-flight-base").first();
            return sectionFlightBase.ownText();
        }
        return null;
    }

    /**
     * 获取航班号
     *
     * @param flightDetailSection
     * @return
     */
    private String getFlightNo(Element flightDetailSection) {
        if (flightDetailSection.is("div.flight-detail-section")) {
            Element flightNo = flightDetailSection.select("p.section-flight-base > span.flight-No").first();
            return flightNo.ownText();
        }
        return null;
    }


    /**
     * 获取机型信息
     *
     * @param flightDetailSection
     * @return
     */
    private String getAircraft(Element flightDetailSection) {
        if (flightDetailSection.is("div.flight-detail-section")) {
            Element airCraft = flightDetailSection.select("p.section-flight-base > span.plane-type > span.abbr").first();
            return airCraft.ownText();
        }
        return null;
    }

    /**
     * 获取日期信息
     *
     * @param departOrArriveEle
     * @return
     */
    private Date getDate(Element departOrArriveEle) {
        String date = departOrArriveEle.select("span.section-date").first().ownText();
        String time = departOrArriveEle.select("span.section-time").first().ownText();
        Date nowDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        //TODO 暂时设置成当前年份
        int year = c.get(Calendar.YEAR);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        date = decorateDate(date);
        String newTime = year + "年" + date + " " + time;
        try {
            return simpleDateFormat.parse(newTime);
        } catch (ParseException e) {
            log.error("解析错误", e);
        }
        return null;
    }

    private String decorateDate(String date) {
        if (date.split("月")[0].length() == 1) {
            date = "0" + date;
        }
        return date;
    }

    /**
     * 获取机场三字码
     *
     * @param departOrArriveEle
     * @return
     */
    private String getAirportCode(Element departOrArriveEle) {
        return departOrArriveEle.select("span.section-airport").first().ownText().split(" ")[0];
    }

    /**
     * 获取机场中文名称
     *
     * @param departOrArriveEle
     * @return
     */
    private String getAirportName(Element departOrArriveEle) {
        return departOrArriveEle.select("span.section-airport").first().ownText().split(" ")[1];
    }

    /**
     * 获取飞机航站楼
     *
     * @param departOrArriveEle
     * @return
     */
    private String getAirportTerminal(Element departOrArriveEle) {
        Elements terminals = departOrArriveEle.select("span.section-terminal");
        if (terminals != null && terminals.size() != 0) {
            return terminals.first().ownText();
        }
        return null;
    }

    /**
     * 获取飞行时长
     *
     * @param departOrArriveEle
     * @return
     */
    private String getDuration(Element departOrArriveEle) {
        String origin = departOrArriveEle.select("span.section-duration").first().ownText();
        if (origin.contains(" ")) {
            return origin.split(" ")[1];
        }
        return null;
    }

    /**
     * 获取出发机场元素信息
     *
     * @param departAndArriveEles
     * @return
     */
    private Element getDepartEle(Elements departAndArriveEles) {
        return departAndArriveEles.get(0);
    }

    /**
     * 获取到达机场元素信息
     *
     * @param departAndArriveEles
     * @return
     */
    private Element getArriveEle(Elements departAndArriveEles) {
        return departAndArriveEles.get(1);
    }

    /**
     * 获取出发和到达机场元素信息
     *
     * @param flightDetailSection
     * @return
     */
    private Optional<Elements> getDepartAndArrive(Element flightDetailSection) {
        Optional<Elements> elementsOptional = Optional.empty();
        if (flightDetailSection.is("div.flight-detail-section")) {
            Elements select = flightDetailSection.select("p:not(.section-flight-base)");
            elementsOptional = Optional.ofNullable(select);
        }
        return elementsOptional;
    }

    /**
     * 获取停留城市城市
     *
     * @param sectionStop
     * @return
     */
    private String getStopCity(Element sectionStop) {
        if (sectionStop.is("div.section-stop")) {
            return sectionStop.select("div.in > strong").first().ownText();
        }
        return null;
    }

    /**
     * 获取停留类型
     *
     * @param sectionStop
     * @return
     */
    private String getStopType(Element sectionStop) {
        if (sectionStop.is("div.section-stop")) {
            return sectionStop.select("div.in").first().ownText().split(" 停留时长：")[0];
        }
        return null;
    }

    /**
     * 获取停留时长
     *
     * @param sectionStop
     * @return
     */
    private String getStopTime(Element sectionStop) {
        if (sectionStop.is("div.section-stop")) {
            return sectionStop.select("div.in").first().ownText().split(" 停留时长：")[1];
        }
        return null;
    }


}