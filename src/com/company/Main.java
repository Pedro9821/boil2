package com.company;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;

@SuppressWarnings("deprecation")
public class Main {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        //x21, x26, x25, x16, x13, x63, x64, x65, x34, x45
        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[]{2, 6, 2, 5, 3, 5, 4, 1, 8, 4}, 0);

        Collection constraints = new ArrayList();
        constraints.add(new LinearConstraint(new double[]{-1, 0, 0, 1, 1, 0, 0, 0, 0, 0}, Relationship.LEQ, 250));
        constraints.add(new LinearConstraint(new double[]{1, 1, 1, 0, 0, 0, 0, 0, 0, 0}, Relationship.LEQ, 300));

        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 1, 1, 0, 0, -1, 0}, Relationship.GEQ, 120));
        constraints.add(new LinearConstraint(new double[]{0, 0, 0, 0, 0, 0, 1, 0, 1, -1}, Relationship.GEQ, 250));
        constraints.add(new LinearConstraint(new double[]{0, 0, 1, 0, 0, 0, 0, 1, 0, 1}, Relationship.GEQ, 100));
        constraints.add(new LinearConstraint(new double[]{0, 1, 0, 1, 0, -1, -1, -1, 0, 0}, Relationship.EQ, 0));


        //create and run solver
        RealPointValuePair solution = null;
        try {
            solution = new SimplexSolver().optimize(f, constraints, GoalType.MINIMIZE, true);
        } catch (OptimizationException e) {
            e.printStackTrace();
        }

        if (solution != null) {
            //get solution
            double min = solution.getValue();
            System.out.println("Wynik: " + min);

            //print decision variables
//            for (int i = 0; i < 2; i++) {
//                System.out.print(solution.getPoint()[i] + "\t");
//            }
        }



    }
}