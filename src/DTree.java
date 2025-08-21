import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DTree {

	public static void main(String[] args) {

        try {
            // Load the decision data from a file
            DecisionTreeNode tree = loadFromFile(args[0]);

            System.out.println("---TREE REPRESENTATION---");
            // Print the tree structure
            printTree(tree, "");
            System.out.println();

            System.out.println("---DECISION TREE COMPUTATION---");
            // Calculate and print expected value
            double expectedValue = calculateExpectedValue(tree);
            System.out.println();
            System.out.printf("Expected Value for '%s': %.2f%n", tree.label, expectedValue);
            
            int lastIndex = tree.resultPath.lastIndexOf("-->");
            
            System.out.printf("Decision for '%s': '%s'", tree.label, tree.resultPath.substring(0, lastIndex) + "" + tree.resultPath.substring(lastIndex + "-->".length()));
        } catch (Exception e) {
            System.err.println("Error reading the decision file: " + e.getMessage());
        }
	}
	
	// Load the decision tree
	static DecisionTreeNode loadFromFile(String filename) {
        BufferedReader reader = null;
        DecisionTreeNode root = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
	        String line;
	        int count=0;
	        while ((line = reader.readLine()) != null) 
	        {
	        	count++;
	        	if(count==1)
	        	{
	        		continue;
	        	}
	            String[] parts = line.split(",");
	            String nodePath = parts[0].trim();
	            
	            boolean isDecision = Boolean.parseBoolean(parts[1].trim());
	            boolean isProduct = Boolean.parseBoolean(parts[2].trim());
	            
	            double probability = isDecision && !isProduct ? 0.0 : Double.parseDouble(parts[3].trim());
	            double netValue = isDecision && !isProduct ? 0.0 : Double.parseDouble(parts[4].trim());
	            double cost = 0.0;
	            
	            List<String> decisionList = Arrays.asList(nodePath.split("->"));
	            
	            if(root == null && decisionList.size()==1)
	            {
	            	root = new DecisionTreeNode(decisionList.get(0), true, false, 0.0, 0.0, 0.0);
	            	continue;
	            }
	            else
	            {	            	
	            	DecisionTreeNode temp = loadData(root, decisionList, 0);
	            	DecisionTreeNode child = new DecisionTreeNode(decisionList.get(decisionList.size()-1), isDecision, isProduct, probability, netValue, cost);
	            	temp.addChild(child);
	            }	           
	        }
	        reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
    }
	
	// Load decision tree in the correct position
	static DecisionTreeNode loadData(DecisionTreeNode root, List<String> decision, int n)
	{
		if(n == decision.size())
		{
			return null;
		}
		
		if(root != null && root.label.equals(decision.get(n)) && n == decision.size()-2)
		{
			return root;
		}
		
		if(root != null && root.label.equals(decision.get(n)) && root.node.size()!=0)
		{
			for (DecisionTreeNode child : root.node) {
				DecisionTreeNode t = loadData(child, decision, n+1);
				if(t!=null)
				{
					return t;
				}
			}
		}
		return null;
	}
	
	// Calculate the expected value of the decision tree
    static double calculateExpectedValue(DecisionTreeNode root) {
        double expectedValue = 0.0;
    	//System.out.println("Root" + "     " + root.label + "    " + root.isDecision + "   " + root.node.size());
        if (!root.isDecision && !root.isProduct)
        {
        	for (DecisionTreeNode child : root.node) {
        		//System.out.println("----" + child.label + "    " + child.isDecision + "   " + child.probability + "    " + child.netValue);
                if (!child.isDecision && child.node.size()==0){
                	//System.out.println(child.label + "    " + child.probability * child.netValue);
                	expectedValue += child.probability * (child.netValue - child.cost);
                }
                else {                	
                    double returnedValue =  calculateExpectedValue(child);
                    expectedValue +=returnedValue;
                    //System.out.println("Returned " + child.label + "    " + returnedValue);
                }
            }
        	root.netValue = root.value==0.0 && root.probability!=0.0 ? root.probability*expectedValue : expectedValue;
        	expectedValue = root.netValue;
        	System.out.printf("    Root label '%s' : %.2f%n", root.label, root.netValue);
        }
        else if(!root.isDecision && root.isProduct)
        {
        	for (DecisionTreeNode child : root.node) {
        		//System.out.println("----" + child.label + "    " + child.isDecision + "   " + child.probability + "    " + child.netValue);
                if (!child.isDecision && child.node.size()==0){
                	expectedValue += child.probability * (child.netValue - child.cost);
                }
                else {
                    expectedValue += calculateExpectedValue(child);
                }
            }
        	root.netValue = expectedValue;
        	System.out.printf("    Root label '%s' : %.2f%n", root.label, root.netValue);
        }
        else
        {
        	double maxValue = Integer.MIN_VALUE;
        	DecisionTreeNode maxChild = null;
        	for (DecisionTreeNode child : root.node) {
        		//System.out.printf("    child label '%s' : %.2f%n", child.label, child.netValue);
                double childExpectedValue = calculateExpectedValue(child);


                // Find the minimum expected value among children
                if (childExpectedValue > maxValue) {
                	maxValue = childExpectedValue;
                	expectedValue = childExpectedValue;
                	maxChild = child;
                }
            }
        	root.netValue = maxValue;
        	root.resultNode = maxChild;
        	root.resultPath.append(root.label + "-->" + maxChild.label + "-->");
        	
        	System.out.printf("    Root label '%s' : Decision - '%s' %n", root.label, root.resultNode.label);
        }
        
        return expectedValue;
    }
    
    // Print Tree Structure
	static void printTree(DecisionTreeNode root, String indent) {		
        if (!root.isDecision && (root.netValue != 0 || root.probability!=0)) {
        	System.out.printf("    -> %s (Probability: %.2f, Net Value: %.2f)%n",
                    root.label, root.probability, root.netValue);
        }
        else
        {
            System.out.println(indent + root.label);
        }
        
        for (DecisionTreeNode child : root.node) {
        	if (child.isDecision) {
                printTree(child, indent + "  ");
            } 
        	else if(child.node.size()==0){
                System.out.printf("%s  -> %s (Probability: %.2f, Net Value: %.2f)%n",
                        indent, child.label, child.probability, child.netValue);
            }
        	else
        	{
        		printTree(child, indent + "  ");
        	}
        }
    }
}
