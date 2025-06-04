package com.home.sabir.springboot.SpringData.component;

import org.springframework.stereotype.Component;

@Component("PrivateSchoolStudentFees")
public class PrivateSchoolStudentFees implements StudentFees {

		@Override
		public Double getFees() {
			return 100.00;
		}
}
