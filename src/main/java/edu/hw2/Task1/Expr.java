package edu.hw2.Task1;

public sealed interface Expr {
    double evaluate();

    record Constant(int num) implements Expr {
        @Override
        public double evaluate() {
            return num;
        }
    }

    record Negate(Expr expr) implements Expr {
        @Override
        public double evaluate() {
            return -expr.evaluate();
        }
    }

    record Exponent(Expr expr, int num) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(expr.evaluate(), num);
        }
    }

    record Addition(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() + expr2.evaluate();
        }
    }

    record Multiplication(Expr expr1, Expr expr2) implements Expr {
        @Override
        public double evaluate() {
            return expr1.evaluate() * expr2.evaluate();
        }
    }
}
