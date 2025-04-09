package com.pawi16.taskapp.taskapp.service.validator;

import com.pawi16.taskapp.taskapp.entity.IssueType;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.IssueException;
import org.springframework.stereotype.Service;

@Service
public class IssueValidatorImpl implements IssueValidator{
    @Override
    public void validateTypeCompatibility(IssueType childType, IssueType parentType) throws BaseException {
        if (parentType == null) {
            if (childType != IssueType.EVENT) {
                // throw issue.type.parent.required
                throw IssueException.validateTypeParentRequired();
            }
            return;
        }

        switch (parentType) {
            case EVENT:
                if (childType == IssueType.EVENT) {
                    // throw event.cannot.have.event.child
                    throw IssueException.validateTypeEventCannotHaveEventChild();
                }
                if (childType == IssueType.SUB_TASK) {
                    // throw subtask.must.be.under.task
                    throw IssueException.validateTypeSubtaskMustBeUnderTask();
                }
                break;

            case TASK:
                if (childType == IssueType.EVENT) {
                    // throw event.cannot.be.child.of.task
                    throw IssueException.validateTypeEventCannotBeChildOfTask();
                }
                if (childType == IssueType.TASK) {
                    // throw task.must.be.under.event
                    throw IssueException.validateTypeTaskMustBeUnderEvent();
                }
                break;

            case SUB_TASK:
                // throw subtask.cannot.have.child
                throw IssueException.validateTypeSubtaskCannotHaveChild();
        }
    }
}
