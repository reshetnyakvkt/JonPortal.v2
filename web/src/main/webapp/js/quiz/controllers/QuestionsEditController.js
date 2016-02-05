/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
function Answer(name, isCorrect){
    this.name = name;
    this.isCorrect = isCorrect;
}
function Question(name, questionType, answerList){
    this.name = name;
    this.questionType = questionType;
    this.answerList = answerList;
}
var questionTypes = {
    items: [
        {value : null, caption: "Единственный"},
        {value : 1, caption: "Множественный"}
    ]
};
quizApp.controller("questionsEditController", function ($scope, $http, $location, $templateCache, $routeParams, dataService) {
    $scope.quiz = dataService.getEditQuiz();
    $scope.newQuestionText;
    $scope.newQuestionType;
    $scope.list = questionTypes;
    $scope.getNameType = function(value){
//        console.info("Start getNameType(" + value + ")");
        if (value === undefined || value == null) value = 0;
        for (var item in $scope.list.items){
//            console.info("getNameType(" + value + ")/ item=" + item.caption);
            if (item.value == value){
                return item.caption;
            }
        }
        return "";
    }
    $scope.newQuestion = function (text, q_type) {
        if (text !== undefined && text != "") {
            if (q_type === undefined || q_type == null) {
                q_type = 0;
            }
            var item = new Question(text, q_type, []);
            $scope.quiz.questions.push(item);
            $scope.refreshNew();
        }
    }
    $scope.delQuestion = function(del_item){
        if (del_item === undefined) return;
        var idx = $scope.quiz.questions.indexOf(del_item);
        if (idx !== undefined && idx >= 0){
            $scope.quiz.questions.remove(idx);
        }
    }
    $scope.refreshNew = function(){
        $scope.newQuestionText = '';
        $scope.newQuestionType = 0;
    }
    $scope.refreshNew();
    $scope.saveExit = function(){
        var promiseObj = dataService.saveQuiz($scope.quiz);
        promiseObj.then(function(value) {
            $location.path("editQuizzes");
        });
    }
});