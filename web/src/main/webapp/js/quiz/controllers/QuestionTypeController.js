/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
 var questionTypes = {
    items: [
        {value : null, caption: "Единственный"},
        {value : 1, caption: "Множественный"}
    ]
};
 quizApp.controller("questionTypeController", function($scope){
        $scope.list = questionTypes;
        $scope.getNameType = function(value){
            console.info("Start getNameType(" + value + ")");
            if (value === undefined || value == null) value = 0;
            for (var item in $scope.list.items){
                console.info("getNameType(" + value + ")/ item=" + item.caption);
                if (item.value == value){
                    return item.caption;
                }
            }
            return "not found";
        }
    });
