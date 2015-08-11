/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javacodegags.waterflooding.handler;

import com.javacodegags.waterflooding.model.Criteria;
import com.javacodegags.waterflooding.model.Parameters;

import java.util.List;

/**
 *
 * @author ������
 */
public class FormulaHendler {

    private Criteria criteria;
    private List<Parameters> lps;

    public FormulaHendler(Criteria criteria, List<Parameters> lps) {
        this.criteria = criteria;
        this.lps = lps;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public List<Parameters> getLps() {
        return lps;
    }

    public void setLps(List<Parameters> lps) {
        this.lps = lps;
    }

    public double toCalculate() {
        if (criteria.getFormula().equals("Трикутна")) {
            return new FTriangular(criteria.getArgument(), lps.get(0).getValue(), lps.get(1).getValue(), lps.get(2).getValue(), criteria.getWeighFactor()).toCompute();
        }
        if (criteria.getFormula().equals("Трапецієвидна")) {
            return new FTrapezoidal(criteria.getArgument(), lps.get(0).getValue(), lps.get(1).getValue(), lps.get(2).getValue(), lps.get(3).getValue(), criteria.getWeighFactor()).toCompute();
        }
        if (criteria.getFormula().equals("Z-подібна")) {
            return new FZViewed(criteria.getArgument(), lps.get(0).getValue(), lps.get(1).getValue(),criteria.getWeighFactor()).toCompute();
        }
        if (criteria.getFormula().equals("S-подібна")) {
            return new FSViewed(criteria.getArgument(), lps.get(0).getValue(), lps.get(1).getValue(),criteria.getWeighFactor()).toCompute();
        }
        if (criteria.getFormula().equals("Лінійна z-подібна")) {
            return new FZViewdLinear(criteria.getArgument(), lps.get(0).getValue(), lps.get(1).getValue(),criteria.getWeighFactor()).toCompute();
        }
        if (criteria.getFormula().equals("Лінійна s-подібна")) {
            return new FSViewedLinear(criteria.getArgument(), lps.get(0).getValue(), lps.get(1).getValue(),criteria.getWeighFactor()).toCompute();
        }
        return 0;
    }

}
