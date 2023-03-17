package com.transport.buspass.dto;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto
{
		private Long id;

		@NotEmpty(message = "Roll No cannot be empty.")
		@Size(min = 5, max = 10, message = "Roll No must be between 5 to 20 characters.")
		private String rollNo;

		private Long registerNo;

		@NotEmpty(message = "Name cannot be empty.")
		@Size(min = 3, max = 30, message = "Name must be between 3 to 30 characters.")
		private String name;

		@NotEmpty(message = "Father's Name cannot be empty.")
		@Size(min = 3, max = 30, message = "Father's Name must be between 4 to 30 characters.")
		private String fatherName;

		@NotNull(message = "College Name cannot be empty.")
		private Integer collegeId;

		@NotNull(message = "Department cannot be empty.")
		private Integer departmentId;

		@NotNull(message = "Semester cannot be null.")
		@Min(value = 1, message = "Semester must be a minimum of 1 digits.")
		@Max(value = 10, message = "Semester must be a maximum of 10 digits.")
		private Integer semester;

		@NotEmpty(message = "Address cannot be empty.")
		@Size(min = 5, max = 50, message = "Address must be between 10 to 100 characters.")
		private String address;

		@NotEmpty(message = "City cannot be empty.")
		@Size(min = 3, max = 30, message = "District must be between 4 to 30 characters.")
		private String district;

		@NotNull(message = "Pincode cannot be null.")
		@Min(value = 100000, message = "Pincode must be a minimum of 6 digits.")
		@Max(value = 999999, message = "Pincode must be a maximum of 6 digits.")
		private Integer pincode;

		@NotNull(message = "Phone Number cannot be null.")
		@Min(value = 6000000000L, message = "Phone No must be a minimum of 10 digits.")
		@Max(value = 9999999999L, message = "Phone No must be a maximum of 10 digits.")
		private Long phoneNo;

		@NotEmpty(message = "Boarding Point cannot be empty.")
		@Size(min = 4, max = 30, message = "Boarding Point must be between 3 to 20 characters.")
		private String boardingPoint;

		@NotNull(message = "Route No cannot be null.")
		private Integer routeId;
		
		@Email
		@NotEmpty(message = "Email cannot be empty.")
		private String email;
		
	    private String verificationCode;
		
}
