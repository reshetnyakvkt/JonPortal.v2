<%--
  Created by IntelliJ IDEA.
  User: Олег
  Date: 16.01.2016
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Test Your Knowledge</title>
    <link href="../css/quiz.css" rel="stylesheet" type="text/css">
    <script src="../js/angular/angular.js"></script>
</head>
<body ng-app="QuizApp">


<div id="quizContainer">
    <h3> User {{currentUser}}</h3>
    <div class="timer {{(activeQuestion >= 0 && activeQuestion < totalQuestions && countDownLeft > 0)
    ? 'active' : 'inactive'}}">У Вас осталось {{countMinutesLeft}} : {{countSecondsLeft}} мин.</div>


    <div class="intro {{(activeQuestion > -1) ? 'inactive' : 'active' }}">
        <h2>{{quizName}}</h2>
        <h3 class="{{timeLimit > 0 && activeQuestion <= -1 ? 'active' : 'inactive'}}">Ограничение по времени: {{timeLimit}} минут</h3>
        <div class="button" ng-click="startQuiz()">Start Test</div>

    </div>
    <div class="question {{questionList[$index].questionState === 'answered' ? 'answered' : 'unanswered' }}
    {{$index === activeQuestion ? 'active' : 'inactive' }}

    " ng-repeat="question in questionList">
        <div>
            <p class="text">{{question.text}}</p>
            <p class="answer {{($index === question.chosenAnswer) ? 'selected' : '' }}"
               ng-click="chooseAnswer($parent.$index, $index)"
               ng-repeat="option in questionList[$index].options">
                {{option}}
            </p>

            <div class="button {{ getQuizCtrlScope().activeQuestion > 0 && getQuizCtrlScope().activeQuestion === $index ? 'active' : 'inactive' }}"
                 ng-click="getQuizCtrlScope().activeQuestion = $index - 1">Previous</div>
            <div class="button" ng-click="confirmAnswer($index)">Confirm</div>
            <div class="button {{ (getQuizCtrlScope().activeQuestion < totalQuestions - 1 &&
         getQuizCtrlScope().activeQuestion === $index) ? 'active' : 'inactive' }}"
                 ng-click="getQuizCtrlScope().activeQuestion = $index + 1">Next</div>
        </div>
    </div>

    <div class="results {{activeQuestion === totalQuestions + 1 ? 'active' : 'inactive'}}">
        <h3>Results</h3>
        <p>Your mark: {{percentage * 100}} %</p>
        <p>Total questions: {{totalQuestions}}</p>
        <p>Correctly answered: {{score}}</p>
        <div class="button" ng-click="toDashboard()">To main menu</div>

    </div>
    <div align="center"  class="progress {{(activeQuestion >= 0 && activeQuestion < totalQuestions) ? 'active' : 'inactive'}}">
        <div class="qItem {{questionList[$index].questionState == 'answered' ? 'answered' : '' }}" ng-repeat="question in questionList"
             ng-click="getQuizCtrlScope().activeQuestion = $index">
            {{$index + 1}}
        </div>
    </div>
</div>
<script src="../js/angular/quizApp.js"></script>
<script src="../js/angular/controllers/quizController.js"></script>
<script src="../js/angular/services/quizes.js"></script>


</body>
</html>