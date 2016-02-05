/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 */
function Answer(text, correct){
    this.text = text;
    this.correct = correct;
}
function Question(text, q_type, answers){
    this.text = text;
    this.q_type = q_type;
    this.answers = answers;
}
/** После отладки убрать----------------------------------------------------------------------------------------------*/
var questions = {
    items: [
        new Question("Какой, четвертый принцип ООП, часто добавляют ко всем извесной троице?", 1, [
            new Answer("Абстракция", true),
            new Answer("Относительность", false),
            new Answer("Изоляция", true),
            new Answer("Композиция", true)
        ]),
        new Question("Как называется 'интерпретация ссылки на объект как ссылки на базовый тип'?", 0, [
            new Answer("Нисходящее преобразование", false),
            new Answer("Восходящее преобразование", true),
            new Answer("Свободное преобразование", false),
            new Answer("Полиморфизм", false),
        ])
    ]
};
/**-------------------------------------------------------------------------------------------------------------------*/
quizApp.controller("quizEditController", function ($scope, $http) {
    $scope.loaded = false;
    $scope.tryCount=0;
    $scope.maxTry=3;

    $scope.quiz;
    $scope.questions = questions;

    $scope.load = function (){
        $scope.tryCount++;
        if ($scope.tryCount > $scope.maxTry){
            return;
        }
        console.info("Started $scope.load--> tryCount=" + $scope.tryCount);

        var conf={
            timeout: 3500 //миллисекунд
        };
        $http.get('j_quiz', conf).success(function(data, status, headers, config) {
                $scope.quiz = data.quiz;
                $scope.questions = data.quiz.questions;
                $scope.loaded = true;
                console.log(config);
            }).error(function(data, status, headers, config) {
                console.log("Ответ от сервера: " + status);
            });
        console.info("<--End $scope.load");
    };

    $scope.textQuiz = "Основы JAVA";

    $scope.saveQuiz = function(text){
        $scope.textQuiz = text;
        alert("Saving " + $scope.textQuiz + " and good by (questions: " + $scope.questions + ")");
    }
});