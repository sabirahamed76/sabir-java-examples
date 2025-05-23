package com.home.sabir.springboot.SpringData.component;

import org.springframework.stereotype.Component;

@Component
public class PrivateSchoolStudentFees implements StudentFees {

		@Override
		public Double getFees() {
			return 100.00;
		}
}
