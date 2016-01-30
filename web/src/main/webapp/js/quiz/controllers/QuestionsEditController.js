/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
quizApp.controller("questionsEditController", function ($scope) {
    $scope.list = questions;
    $scope.addQuestion = function (text, q_type) {
        if(text !== undefined && text != "")
        {
            if (q_type === undefined || q_type == null){
                q_type = 0;
            }
            var item = new Question(text, q_type);
            $scope.list.items.push(item);
        }
    }
    $scope.delQuestion = function(del_item){
        if (del_item === undefined) return;
        var idx = $scope.list.items.indexOf(del_item);
        if (idx !== undefined && idx >= 0){
            $scope.list.items.remove(idx);
        }
    }
});