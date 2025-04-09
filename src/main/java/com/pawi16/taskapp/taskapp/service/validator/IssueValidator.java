package com.pawi16.taskapp.taskapp.service.validator;

import com.pawi16.taskapp.taskapp.entity.IssueType;
import com.pawi16.taskapp.taskapp.exception.BaseException;

public interface IssueValidator {
    void validateTypeCompatibility(IssueType childType, IssueType parentType) throws BaseException;
}
