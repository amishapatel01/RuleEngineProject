Creating a New Rule
This endpoint allows you to create a rule by defining conditions using logical operators and attributes. The rule is represented as a string with conditional statements involving attributes like age, department, salary, and experience.

Endpoint: POST /api/rules/create

Description: This endpoint accepts a ruleString in JSON format. The rule string contains logical conditions that combine multiple attributes using operators like AND and OR. For example, in this rule:

java
Copy code
((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)
a user is eligible if they meet any one of the conditions in the OR clauses.

cURL Example:

bash
Copy code
curl --location 'http://localhost:8080/api/rules/create' \
--header 'Content-Type: application/json' \
--data '{
"ruleString": "((age > 30 AND department = '\''Sales'\'') OR (age < 25 AND department = '\''Marketing'\'')) AND (salary > 50000 OR experience > 5)"
}'
This command sends the rule to the server, which saves it for future evaluations.
...........................................................


Combining Existing Rules
This endpoint enables the combination of multiple existing rules into a single composite rule. The rules are combined using logical operators (AND or OR) based on the defined logic within the backend.

Endpoint: POST /api/rules/combine

Description: This endpoint accepts a JSON array of rule IDs representing individual rules that will be combined into a single rule. Each rule is identified by its unique ID, and they are logically merged according to backend logic, such as using OR to form a broader eligibility condition.

cURL Example:

bash
Copy code
curl --location 'http://localhost:8080/api/rules/combine' \
--header 'Content-Type: application/json' \
--data '[
1,
2
]'
This command sends the list of rule IDs (in this example, rule IDs 1 and 2) to be combined. The backend returns a new rule entity containing the composite rule string for further evaluation or use.
.............................................................................


Evaluating a Rule
This endpoint evaluates a specified rule against user data attributes, determining if the data satisfies the rule's conditions.

Endpoint: POST /api/rules/evaluate/{ruleId}

Description: This endpoint accepts a ruleId in the URL path, referencing the rule to evaluate, and a JSON payload in the request body that provides the user data for evaluation. The data is checked against the conditions defined in the rule. If the data meets the rule criteria, the response will be true; otherwise, it will be false.

cURL Example:

bash
Copy code
curl --location 'http://localhost:8080/api/rules/evaluate/1' \
--header 'Content-Type: application/json' \
--data '{
"age": 30,
"department": "Sales",
"salary": 60000,
"experience": 3
}'
In this example, rule with ID 1 will be evaluated to see if the provided attributes (age: 30, department: "Sales", salary: 60000, experience: 3) satisfy its conditions.







