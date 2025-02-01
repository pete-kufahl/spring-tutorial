# loans project
<img src="./hi level design.jpg" alt="hi level design" />

## System Architecture
1. User submits a loan application via the React UI.
2. The Spring Boot backend stores the application and triggers risk evaluation.
3. The Python service calculates a risk score (via ML model or rule-based scoring).
4. The result is sent back to the backend, which decides approval or rejection.
    - there will also be an asynchronous Reporter service for emails, another database, etc.
5. The UI updates with the loan status.
## loanz: Spirng Boot Backend