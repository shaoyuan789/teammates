package teammates.common.datatransfer;

import teammates.common.util.Sanitizer;

public class FeedbackMcqResponseDetails extends FeedbackAbstractResponseDetails {
    private String answer;
    private boolean isOther;
    private String otherFieldContent; //content of other field if "other" is selected as the answer
    
    public FeedbackMcqResponseDetails() {
        super(FeedbackQuestionType.MCQ);
        answer = "";
        isOther = false;
        otherFieldContent = "";
    }
    
    @Override
    public boolean extractResponseDetails(FeedbackQuestionType questionType,
            FeedbackAbstractQuestionDetails questionDetails, String[] answer) {
        // TODO: check and set isOther accordingly when it is implemented.
        if(isOther){
            this.answer = "Other";
            this.otherFieldContent = answer[0];
        } else {
            this.answer = answer[0];
            this.otherFieldContent = "";
        }
        return true;
    }

    @Override
    public String getAnswerString() {
        if(isOther){
            return otherFieldContent;
        } else {
            return answer;
        }
    }

    @Override
    public String getAnswerHtml(FeedbackAbstractQuestionDetails questionDetails) {
        return Sanitizer.sanitizeForHtml(getAnswerString());
    }

    @Override
    public String getAnswerCsv(FeedbackAbstractQuestionDetails questionDetails) {
        return Sanitizer.sanitizeForCsv(getAnswerString());
    }
}
