package com.home.sabir.springboot.SpringData.component;

import org.springframework.stereotype.Component;

@Component("PublicSchoolStudentFees")
public class PublicSchoolStudentFees implements StudentFees {

	@Override
	public Double getFees() {
		return 10.00;
	}
}
