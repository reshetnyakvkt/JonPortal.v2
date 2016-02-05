/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
quizApp.controller("answersEditController", function ($scope) {
    $scope.questionType = 0;
    $scope.addAnswer = function (answers, questionType, text, correct) {
        if(text !== undefined && text != "")
        {
            var item = new Answer(text, correct);
            answers.push(item);
            this.setCorrectAnswer(answers, questionType, item);
        }
    }
    $scope.delAnswer = function(answers, questionType, del_item){
        console.info("Start delAnswer() -->" + answers);
        if (del_item === undefined) return;
        var idx = answers.indexOf(del_item);
        if (idx !== undefined && idx >= 0){
            answers.remove(idx);
            if (answers.length > 0){
                this.setCorrectAnswer(answers, questionType, answers[0]);
            }
        }
        console.info("<--End delAnswer()");
    }
    $scope.setCorrectAnswer = function(answers, questionType, set_item){
        console.info("Start setCorrectAnswer() -->");


        console.info("Label setCorrectAnswer() 1");
        var item_correct;
        var item;
        if (questionType == 0){
            console.info("Label setCorrectAnswer() 2");
            if ( set_item !== undefined && set_item.correct) {
                item_correct = set_item;
                console.info("set_item.correct=true");
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
                        console.info(item + "/item.correct=false");
                    }
                }
            }
        }
        if (questionType == 0 && item_correct === undefined) {
            console.info("Label setCorrectAnswer() 10");
            if (set_item !== undefined){
                set_item.correct = true;
            } else {
                if (answers.length > 0){
                    answers[0].correct = true;
                }
            }
        }
    }
});
