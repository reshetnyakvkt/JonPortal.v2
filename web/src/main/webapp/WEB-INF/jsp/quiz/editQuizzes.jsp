<%--
 Created by Reshetnyak Viktor on 30.01.2016.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-inline">
    <div class="form-group">
        <label>Этап</label>
        <select class="form-control" ng-model="q_type">
            <option ng-repeat="sprint in sprints.items" value="{{sprint.id}}">{{sprint.name}}</option>
        </select>
    </div>
</div>
<br/>
<div class="panel">
    <form name="quizForm">
        <div class="form-inline">
            <div class="form-group">
                <textarea rows="2" cols="70" class="form-control" ng-model="newQuizText" required placeholder="Название теста"></textarea>
            </div>
            <label>Время в мин</label>
            <div class="form-group">
                <input type="number" class="form-control" required ng-model="newQuizLimit"/>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-8">
                    <button type="submit" class="btn btn-success" ng-click="newQuiz(newQuizText, newQuizLimit, quizForm)">Добавить</button>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="panel">
    <div class="form-inline">
        <h5>Список тестов</h5><br>
        <div class="form-group">
            <table class="table table-striped">
                <thead>
                <tr class="active">
                    <th>ИД</th>
                    <th>Тест</th>
                    <th>Время</th>
                    <th></th>
                </tr>
                </thead>
                <tr ng-repeat="quiz in quizzes track by $index">
                    <td>{{quiz.id}}</td>
                    <td>{{quiz.description}}</td>
                    <td>{{quiz.timeLimit}}</td>
                    <td>
                        <button class="btn btn-default btn-sm" ng-click="editQuiz(quiz)">Изменить</button>
                        <button class="btn btn-danger btn-sm" ng-click="delQuiz(quiz)">Удалить</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
