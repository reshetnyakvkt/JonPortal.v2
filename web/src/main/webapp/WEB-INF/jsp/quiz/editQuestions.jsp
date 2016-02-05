<!--
Created by Reshetnyak Viktor on 25.01.2016!
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel">
    <form name="quizForm">
        <div class="form-inline">
            <div class="form-group">
                <textarea rows="2" cols="70" class="form-control" ng-model="quiz.description" required placeholder="Название теста"></textarea>
            </div>
            <label>Время в мин</label>
            <div class="form-group">
                <input type="number" class="form-control" required ng-model="quiz.timeLimit"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-8">
                    <button type="submit" class="btn btn-success" ng-click="saveExit()">Сохранить<br>и выйти</button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="page-header">
    <h4>Список вопросов</h4>
</div>
<div class="panel">

    <form name="questionForm">
        <div class="form-inline">
            <div class="form-group">
                <textarea rows="2" cols="70" class="form-control" ng-model="newQuestionText" placeholder="Вопрос"></textarea>
            </div>
            <%--<div ng-controller="questionTypeController">--%>
                <select class="form-control" ng-model="q_type">
                    <label>Тип ответа</label>
                    <option ng-repeat="question_type in list.items" value="{{question_type.value}}">{{question_type.caption}}</option>
                </select>
            <%--</div>--%>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-8">
                    <button class="btn btn-success" ng-click="newQuestion(newQuestionText, question_type.value)">Добавить вопрос</button>
                </div>
            </div>
        </div>
    </form>


    <table class="table table-bordered">
        <thead>
        <tr class="active">
            <th>Вопрос</th>
            <th>Тип ответа</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="question in quiz.questions">
            <td>{{question.name}}<br>

                <div ng-controller="answersEditController">
                    <div class="form-inline" name="answerForm">
                        <div class="form-group">
                            <input class="form-control" ng-model="newAnswerText" placeholder="Ответ"/>
                        </div>
                        <div class="form-group">
                            <label>Верный</label>
                            <input type="checkbox" class="form-control" ng-model="newAnswerCorrect"/>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-8">
                                <button class="btn btn-success btn-sm"
                                        ng-click="newAnswer(question.answerList, question.q_type, newAnswerText, newAnswerCorrect)">
                                    Добавить ответ
                                </button>
                            </div>
                        </div>
                    </div>

                    <table class="table table-condensed" class="active">
                        <thead>
                        <tr class="active">
                            <th>Ответ</th>
                            <th>Верный</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tr ng-repeat="answer in question.answerList">
                            <td>{{answer.name}}</td>
                            <td><input type="checkbox" ng-model="answer.correct" ng-click="setCorrectAnswer(question.answerList, question.q_type, answer)"/></td>
                            <td><button class="btn btn-warning btn-sm"
                                        ng-click="delAnswer(question.answerList, question.q_type, answer)">Удалить ответ</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
            <td>{{question.q_type}}<br/>
                <%--<div ng-controller="questionTypeController">--%>
                    {{getNameType(question.q_type)}}
                <%--</div>--%>
            </td>
            <td><button class="btn btn-danger" ng-click="delQuestion(question)">Удалить<br>вопрос</button></td>
        </tr>
        </tbody>
    </table>
</div>
