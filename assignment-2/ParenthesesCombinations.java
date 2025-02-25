import java.util.ArrayList;
import java.util.List;

public class ParenthesesCombinations{

    int numOpen = 0;
    int numClose = 0;

    public ParenthesesCombinations(){
        // here
    }

    public List<String> generateParentheses(int n){

        ArrayList<String> solutions = new ArrayList<String>();
        StringBuilder newSolution = new StringBuilder();

        backtrackToFindCombinations(solutions, newSolution, numOpen, numClose, n);
        
        return solutions;
    }

    private void backtrackToFindCombinations(ArrayList<String> solutions, StringBuilder newCombination, int numO, int numC, int n){

        if(numO == n && numC == n){

            String answer = newCombination.toString();
            solutions.add(answer);

            return;
        }

        if(numO < n){
            newCombination.append("(");

            backtrackToFindCombinations(solutions, newCombination, numO + 1, numC, n);

            newCombination.deleteCharAt(newCombination.length() - 1);
        }

        if(numC < numO){
            newCombination.append(")");

            backtrackToFindCombinations(solutions, newCombination, numO, numC + 1, n);

            newCombination.deleteCharAt(newCombination.length() - 1);
        }
    }
}