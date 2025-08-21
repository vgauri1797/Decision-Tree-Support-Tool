Decision Tree Files :- decisiontree.txt(Question 2 tree), decisiontree-1.txt(Class Yugo+Guarantee Example tree)

DECISION TREE FILE FORMAT
Node Path: Describes the sequence of decisions and outcomes leading to a particular node in the decision tree. Each path reflects a choice made (e.g., purchasing a Yugo with or without insurance).

Is Decision: Indicates whether a particular node represents a decision point.

Is Inbetween Node: Indicates whether a node is an intermediary point in the decision process.

Probability: This column represents the likelihood of picking a choice(which is the label at the pointed at the end of Node Path value) given the preceding decisions. For example, in the path "Purchase Yugo->With Insurance->Peach," the probability of picking Peach occurring is 0.8.

Net Value: This column indicates the net value associated with each outcome. It reflects the financial impact resulting from the decision. For example, "Purchase Yugo->Without Insurance->Lemon" has a net value of -100, indicating a loss.

Net Value provides the Cost evaluated based on the Cost(Including all miscellaneous costs) and Sell Price in case of Yugo Example and the investment and profit acheived based on the outcomes for a whole year in the Question 2.


------------------------decisiontree.txt result-------------------------
---TREE REPRESENTATION---
Develop Product
  High-End Laptop
    With Marketing
    -> With Regulations (Probability: 0.40, Net Value: 0.00)
        -> Competitor Release (Probability: 0.35, Net Value: -1290000.00)
        -> Success (Probability: 0.65, Net Value: -1030000.00)
    -> Without Regulations (Probability: 0.60, Net Value: 0.00)
        -> Competitor Release (Probability: 0.35, Net Value: -1070000.00)
        -> Success (Probability: 0.65, Net Value: -770000.00)
    Without Marketing
    -> With Regulations (Probability: 0.40, Net Value: 0.00)
        -> Competitor Release (Probability: 0.35, Net Value: -1040000.00)
        -> Success (Probability: 0.65, Net Value: -780000.00)
    -> Without Regulations (Probability: 0.60, Net Value: 0.00)
        -> Competitor Release (Probability: 0.35, Net Value: -820000.00)
        -> Success (Probability: 0.65, Net Value: -520000.00)
  Budget Tablet
    With Marketing
    -> With Regulations (Probability: 0.40, Net Value: 0.00)
        -> Market Acceptance (Probability: 0.80, Net Value: -650000.00)
        -> Market Failure (Probability: 0.20, Net Value: -770000.00)
    -> Without Regulations (Probability: 0.60, Net Value: 0.00)
        -> Market Acceptance (Probability: 0.80, Net Value: -450000.00)
        -> Market Failure (Probability: 0.20, Net Value: 0.00)
    Without Marketing
    -> With Regulations (Probability: 0.40, Net Value: 0.00)
        -> Market Acceptance (Probability: 0.80, Net Value: -400000.00)
        -> Market Failure (Probability: 0.20, Net Value: -520000.00)
    -> Without Regulations (Probability: 0.60, Net Value: 0.00)
        -> Market Acceptance (Probability: 0.80, Net Value: -200000.00)
        -> Market Failure (Probability: 0.20, Net Value: -360000.00)
  Smart Home Device
    With Marketing
    -> With Regulations (Probability: 0.40, Net Value: 0.00)
        -> With Partnership (Probability: 0.60, Net Value: -610000.00)
        -> Without Partnership (Probability: 0.40, Net Value: -980000.00)
    -> Without Regulations (Probability: 0.60, Net Value: 0.00)
        -> With Partnership (Probability: 0.60, Net Value: -370000.00)
        -> Without Partnership (Probability: 0.40, Net Value: -820000.00)
    Without Marketing
      With Regulations
        -> With Partnership (Probability: 0.60, Net Value: -360000.00)
        -> Without Partnership (Probability: 0.40, Net Value: -730000.00)
      Without Regulations
        -> With Partnership (Probability: 0.60, Net Value: -120000.00)
        -> Without Partnership (Probability: 0.40, Net Value: -570000.00)

---DECISION TREE COMPUTATION---
    Root label 'With Regulations' : -448400.00
    Root label 'Without Regulations' : -525000.00
    Root label 'With Marketing' : -973400.00
    Root label 'With Regulations' : -348400.00
    Root label 'Without Regulations' : -375000.00
    Root label 'Without Marketing' : -723400.00
    Root label 'High-End Laptop' : Decision - 'Without Marketing'
    Root label 'With Regulations' : -269600.00
    Root label 'Without Regulations' : -216000.00
    Root label 'With Marketing' : -485600.00
    Root label 'With Regulations' : -169600.00
    Root label 'Without Regulations' : -139200.00
    Root label 'Without Marketing' : -308800.00
    Root label 'Budget Tablet' : Decision - 'Without Marketing'
    Root label 'With Regulations' : -303200.00
    Root label 'Without Regulations' : -330000.00
    Root label 'With Marketing' : -633200.00
    Root label 'With Regulations' : -508000.00
    Root label 'Without Regulations' : -300000.00
    Root label 'Without Marketing' : -808000.00
    Root label 'Smart Home Device' : Decision - 'With Marketing'
    Root label 'Develop Product' : Decision - 'Budget Tablet'

Expected Value for 'Develop Product': -308800.00
Decision for 'Develop Product': 'Develop Product-->Budget Tablet'


------------------------decisiontree-1.txt result------------------------
---TREE REPRESENTATION---
Purchase Yugo
  With Insurance
    -> Lemon (Probability: 0.20, Net Value: 40.00)
    -> Peach (Probability: 0.80, Net Value: 20.00)
  Without Insurance
    -> Lemon (Probability: 0.20, Net Value: -100.00)
    -> Peach (Probability: 0.80, Net Value: 60.00)
  -> Don't Buy (Probability: 0.00, Net Value: 0.00)

---DECISION TREE COMPUTATION---
    Root label 'With Insurance' : 24.00
    Root label 'Without Insurance' : 28.00
    Root label 'Don't Buy' : 0.00
    Root label 'Purchase Yugo' : Decision - 'Without Insurance'

Expected Value for 'Purchase Yugo': 28.00
Decision for 'Purchase Yugo': 'Purchase Yugo-->Without Insurance'