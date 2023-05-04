package com.project.se2project.domain.Loan;

import java.io.Serializable;

public class CheckLoanDateResponse implements Serializable {

        private String message;

        private boolean check;

        private long nextPayment;

        public CheckLoanDateResponse() {

        }

        public CheckLoanDateResponse(String message, boolean check, long nextPayment) {
            this.message = message;
            this.check = check;
            this.nextPayment = nextPayment;
        }

        public CheckLoanDateResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public boolean getCheck() {
            return check;
        }

        public long getNextPayment() {
            return nextPayment;
        }
}
