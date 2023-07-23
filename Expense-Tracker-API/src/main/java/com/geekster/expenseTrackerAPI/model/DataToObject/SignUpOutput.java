package com.geekster.expenseTrackerAPI.model.DataToObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpOutput {
    private  boolean signUpStatus;
    private  String signUpStatusMessage;
}
