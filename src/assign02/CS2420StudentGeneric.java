package assign02;

/**
 * Creates a student with a generic contact info type in the 2420 class
 * 
 * @author CS 2420 course staff and Benjamin Faerber and David Chen
 * @version 2025-05-15
 */
public class CS2420StudentGeneric<Type> extends UofUStudent {

    private Type contactInfo;
    private double assignmentScore, examScore, labScore, quizScore = 0;
    private int assignCount, examCount, labCount, quizCount;

    public CS2420StudentGeneric(String firstName, String lastName, int uNID, Type contactInfo) {
        super(firstName, lastName, uNID);
        this.contactInfo = contactInfo;

    }

    /**
     * Get the contact info
     * @return the students contact info
     */
    public Type getContactInfo() {
        return this.contactInfo;
    }

    /**
     * Adds the score of a given category (ie assignment)
     * @param score 0-100
     * @param category "assignment" or "lab" or "quiz" or "exam"
     */
    public void addScore(double score, String category) {
        switch (category) {
            case "assignment": this.assignmentScore += score; this.assignCount++; break;
            case "exam": this.examScore += score; this.examCount++; break;
            case "lab": this.labScore += score; this.labCount++; break;
            case "quiz": this.quizScore += score; this.quizCount++; break;
            default: break;
        }
    }

    /**
     * Compute the students final score out of 100
     * @return the score 0-100
     */
    public double computeFinalScore() {
        if (assignCount == 0 || examCount == 0 || labCount == 0 || quizCount == 0) return 0;
        return (this.assignmentScore/this.assignCount * 0.35 + this.examScore/this.examCount * 0.5 +
                this.labScore/this.labCount * 0.075 + this.quizScore/quizCount * 0.075);
    }

    /**
     * Compute the letter grade of the student
     * @return a string A to E (or N/A in the event of a missing score)
     */
    public String computeFinalGrade() {
        if (assignCount == 0 || examCount == 0 || labCount == 0 || quizCount == 0) return "N/A";

        double finalScore = this.computeFinalScore();
        if (finalScore >= 93) {
            return "A";
        } else if (finalScore >= 90) {
            return "A-";
        } else if (finalScore >= 87) {
            return "B+";
        } else if (finalScore >= 83) {
            return "B";
        } else if (finalScore >= 80) {
            return "B-";
        } else if (finalScore >= 77) {
            return "C+";
        } else if (finalScore >= 73) {
            return "C";
        }else if (finalScore >= 70) {
            return "C-";
        }else if (finalScore >= 67) {
            return "D+";
        }else if (finalScore >= 63) {
            return "D";
        }else if (finalScore >= 60) {
            return "D-";
        }
        return "E";
    }

}
