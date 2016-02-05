/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
quizApp.controller("answersEditController", function ($scope) {
    $scope.newAnswerText;
    $scope.newAnswerCorrect;
    $scope.questionType = 0;
    $scope.newAnswer = function (answers, questionType, text, correct) {
        if (text !== undefined && text != "") {
            var item = new Answer(text, correct);
            answers.push(item);
            this.setCorrectAnswer(answers, questionType, item);
            $scope.refreshNew();
        }
    }
    $scope.delAnswer = function(answers, questionType, del_item){
        if (del_item === undefined) return;
        var idx = answers.indexOf(del_item);
        if (idx !== undefined && idx >= 0){
            answers.remove(idx);
            if (answers.length > 0){
                this.setCorrectAnswer(answers, questionType, answers[0]);
            }
        }
    }
    $scope.setCorrectAnswer = function(answers, questionType, set_item){
        var item_correct;
        var item;
        if (questionType == 0){
            if ( set_item !== undefined && set_item.correct) {
                item_correct = set_item;
            }
        }
        for (var i = 0; i < answers.length; i++) {
            item = answers[i];

            if (questionType == 0) {
                if (item_correct === undefined){
                    if (item.correct) {
                        item_correct = item;
                    }
                } else {
                    if (item_correct !== item){
                        item.correct = false;
                    }
                }
            }
        }
        if (questionType == 0 && item_correct === undefined) {
            if (set_item !== undefined){
                set_item.correct = true;
            } else {
                if (answers.length > 0){
                    answers[0].correct = true;
                }
            }
        }
    }
    $scope.refreshNew = function(){
        $scope.newAnswerText = '';
        $scope.newAnswerCorrect = false;
    };
    $scope.refreshNew();
});
