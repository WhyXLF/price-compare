package com.neuq.flight.grab.domain;

import java.util.ArrayList;
import java.util.List;

public class SearchResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SearchResultExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTripTypeIsNull() {
            addCriterion("trip_type is null");
            return (Criteria) this;
        }

        public Criteria andTripTypeIsNotNull() {
            addCriterion("trip_type is not null");
            return (Criteria) this;
        }

        public Criteria andTripTypeEqualTo(Integer value) {
            addCriterion("trip_type =", value, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeNotEqualTo(Integer value) {
            addCriterion("trip_type <>", value, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeGreaterThan(Integer value) {
            addCriterion("trip_type >", value, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trip_type >=", value, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeLessThan(Integer value) {
            addCriterion("trip_type <", value, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeLessThanOrEqualTo(Integer value) {
            addCriterion("trip_type <=", value, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeIn(List<Integer> values) {
            addCriterion("trip_type in", values, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeNotIn(List<Integer> values) {
            addCriterion("trip_type not in", values, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeBetween(Integer value1, Integer value2) {
            addCriterion("trip_type between", value1, value2, "tripType");
            return (Criteria) this;
        }

        public Criteria andTripTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("trip_type not between", value1, value2, "tripType");
            return (Criteria) this;
        }

        public Criteria andBackDateIsNull() {
            addCriterion("back_date is null");
            return (Criteria) this;
        }

        public Criteria andBackDateIsNotNull() {
            addCriterion("back_date is not null");
            return (Criteria) this;
        }

        public Criteria andBackDateEqualTo(String value) {
            addCriterion("back_date =", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateNotEqualTo(String value) {
            addCriterion("back_date <>", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateGreaterThan(String value) {
            addCriterion("back_date >", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateGreaterThanOrEqualTo(String value) {
            addCriterion("back_date >=", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateLessThan(String value) {
            addCriterion("back_date <", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateLessThanOrEqualTo(String value) {
            addCriterion("back_date <=", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateLike(String value) {
            addCriterion("back_date like", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateNotLike(String value) {
            addCriterion("back_date not like", value, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateIn(List<String> values) {
            addCriterion("back_date in", values, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateNotIn(List<String> values) {
            addCriterion("back_date not in", values, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateBetween(String value1, String value2) {
            addCriterion("back_date between", value1, value2, "backDate");
            return (Criteria) this;
        }

        public Criteria andBackDateNotBetween(String value1, String value2) {
            addCriterion("back_date not between", value1, value2, "backDate");
            return (Criteria) this;
        }

        public Criteria andGoDateIsNull() {
            addCriterion("go_date is null");
            return (Criteria) this;
        }

        public Criteria andGoDateIsNotNull() {
            addCriterion("go_date is not null");
            return (Criteria) this;
        }

        public Criteria andGoDateEqualTo(String value) {
            addCriterion("go_date =", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateNotEqualTo(String value) {
            addCriterion("go_date <>", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateGreaterThan(String value) {
            addCriterion("go_date >", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateGreaterThanOrEqualTo(String value) {
            addCriterion("go_date >=", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateLessThan(String value) {
            addCriterion("go_date <", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateLessThanOrEqualTo(String value) {
            addCriterion("go_date <=", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateLike(String value) {
            addCriterion("go_date like", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateNotLike(String value) {
            addCriterion("go_date not like", value, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateIn(List<String> values) {
            addCriterion("go_date in", values, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateNotIn(List<String> values) {
            addCriterion("go_date not in", values, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateBetween(String value1, String value2) {
            addCriterion("go_date between", value1, value2, "goDate");
            return (Criteria) this;
        }

        public Criteria andGoDateNotBetween(String value1, String value2) {
            addCriterion("go_date not between", value1, value2, "goDate");
            return (Criteria) this;
        }

        public Criteria andToCityCodeIsNull() {
            addCriterion("to_city_code is null");
            return (Criteria) this;
        }

        public Criteria andToCityCodeIsNotNull() {
            addCriterion("to_city_code is not null");
            return (Criteria) this;
        }

        public Criteria andToCityCodeEqualTo(String value) {
            addCriterion("to_city_code =", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeNotEqualTo(String value) {
            addCriterion("to_city_code <>", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeGreaterThan(String value) {
            addCriterion("to_city_code >", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("to_city_code >=", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeLessThan(String value) {
            addCriterion("to_city_code <", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeLessThanOrEqualTo(String value) {
            addCriterion("to_city_code <=", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeLike(String value) {
            addCriterion("to_city_code like", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeNotLike(String value) {
            addCriterion("to_city_code not like", value, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeIn(List<String> values) {
            addCriterion("to_city_code in", values, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeNotIn(List<String> values) {
            addCriterion("to_city_code not in", values, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeBetween(String value1, String value2) {
            addCriterion("to_city_code between", value1, value2, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andToCityCodeNotBetween(String value1, String value2) {
            addCriterion("to_city_code not between", value1, value2, "toCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeIsNull() {
            addCriterion("from_city_code is null");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeIsNotNull() {
            addCriterion("from_city_code is not null");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeEqualTo(String value) {
            addCriterion("from_city_code =", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeNotEqualTo(String value) {
            addCriterion("from_city_code <>", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeGreaterThan(String value) {
            addCriterion("from_city_code >", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("from_city_code >=", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeLessThan(String value) {
            addCriterion("from_city_code <", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeLessThanOrEqualTo(String value) {
            addCriterion("from_city_code <=", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeLike(String value) {
            addCriterion("from_city_code like", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeNotLike(String value) {
            addCriterion("from_city_code not like", value, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeIn(List<String> values) {
            addCriterion("from_city_code in", values, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeNotIn(List<String> values) {
            addCriterion("from_city_code not in", values, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeBetween(String value1, String value2) {
            addCriterion("from_city_code between", value1, value2, "fromCityCode");
            return (Criteria) this;
        }

        public Criteria andFromCityCodeNotBetween(String value1, String value2) {
            addCriterion("from_city_code not between", value1, value2, "fromCityCode");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}