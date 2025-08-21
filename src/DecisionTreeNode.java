import java.util.ArrayList;
import java.util.List;

public class DecisionTreeNode {
	String label;
	boolean isDecision;
	boolean isProduct;
	double probability;
	double value;
	double netValue;
	double cost;
	DecisionTreeNode resultNode;
	StringBuilder resultPath;
    List<DecisionTreeNode> node;
    
    DecisionTreeNode()
    {
    	
    }
    
    DecisionTreeNode(String label, boolean isDecision, boolean isProduct, double probability, double value, double cost) {
        this.label = label;
        this.isDecision = isDecision;
        this.isProduct = isProduct;
        this.probability = probability;
        this.value = value;
        this.netValue = value;
        this.cost = cost;
        this.node = new ArrayList<DecisionTreeNode>();
        this.resultPath = new StringBuilder();
    }

    void addChild(DecisionTreeNode childNode) {
        node.add(childNode);
    }
}
