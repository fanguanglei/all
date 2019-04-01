package com.xw.dto;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 查询封装类
 */
public class SimpleSpecification<T> implements Specification<T> {

	/**
	 * 鏌ヨ鐨勬潯浠跺垪琛紝鏄竴缁勫垪琛�
	 */
	private List<SpecificationOperator> opers;

	public SimpleSpecification(List<SpecificationOperator> opers) {
		this.opers = opers;
	}

	/**
	 * 閲嶅啓toPredicate锛屾瀯閫犳煡璇㈡潯浠�
	 * 
	 * @time 2018骞�4鏈�10鏃� 涓嬪崍5:57:11. </br>
	 * @version V1.0</br>
	 * @param root
	 * @param criteriaQuery
	 * @param criteriaBuilder
	 * @return Predicate</br>
	 */
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		int index = 0;
		// 閫氳繃resultPre鏉ョ粍鍚堝涓潯浠�
		Predicate resultPre = null;
		for (SpecificationOperator op : opers) {
			if (index++ == 0) {
				resultPre = generatePredicate(root, criteriaBuilder, op);
				continue;
			}
			Predicate pre = generatePredicate(root, criteriaBuilder, op);
			if (pre == null)
				continue;
			if (SpecificationOperator.Join.and.name().equalsIgnoreCase(op.getJoin())) {
				resultPre = criteriaBuilder.and(resultPre, pre);
			} else if (SpecificationOperator.Join.or.name().equalsIgnoreCase(op.getJoin())) {
				resultPre = criteriaBuilder.or(resultPre, pre);
			}
		}
		return resultPre;
	}

	private Predicate generatePredicate(Root<T> root, CriteriaBuilder criteriaBuilder, SpecificationOperator op) {
		/*
		 * 鏍规嵁涓嶅悓鐨勬搷浣滅杩斿洖鐗瑰畾鐨勬煡璇�
		 */
		if (SpecificationOperator.Operator.eq.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.equal(root.get(op.getKey()), op.getValue());
		} else if (SpecificationOperator.Operator.ge.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.ge(root.get(op.getKey()).as(Number.class), (Number) op.getValue());
		} else if (SpecificationOperator.Operator.le.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.le(root.get(op.getKey()).as(Number.class), (Number) op.getValue());
		} else if (SpecificationOperator.Operator.gt.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.gt(root.get(op.getKey()).as(Number.class), (Number) op.getValue());
		} else if (SpecificationOperator.Operator.lt.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.lt(root.get(op.getKey()).as(Number.class), (Number) op.getValue());
		} else if (SpecificationOperator.Operator.likeAll.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.like(root.get(op.getKey()).as(String.class), "%" + op.getValue() + "%");
		} else if (SpecificationOperator.Operator.likeL.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.like(root.get(op.getKey()).as(String.class), op.getValue() + "%");
		} else if (SpecificationOperator.Operator.likeR.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.like(root.get(op.getKey()).as(String.class), "%" + op.getValue());
		} else if (SpecificationOperator.Operator.isNull.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.isNull(root.get(op.getKey()));
		} else if (SpecificationOperator.Operator.isNotNull.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.isNotNull(root.get(op.getKey()));
		} else if (SpecificationOperator.Operator.notEqual.name().equalsIgnoreCase(op.getOper())) {
			return criteriaBuilder.notEqual(root.get(op.getKey()), op.getValue());
		}
		return null;
	}

}