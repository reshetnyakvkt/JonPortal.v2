/**
 * Created by Олег on 17.01.2016.
 */
quizApp.controller('quizController', ['$scope', '$routeParams', 'users', 'quizes', '$timeout', '$location',
    function($scope, $routeParams, users, quizes, $timeout, $location) {


    var quizId = $routeParams.quizId;
    $scope.score = 0;
    $scope.activeQuestion = -1;
    $scope.questionsAnswered = 0;
    $scope.percentage = 0;
    $scope.timerCount = 0;
    $scope.currentUser = users.getCurrentUser();
    $scope.userQuiz = {
        user: '',
        quiz: '',
        date: '',
        score: ''
    };

    $scope.getQuizCtrlScope = function() {
        return $scope;
    };

    $scope.chooseAnswer = function(qIndex, aIndex) {
        var questionState = $scope.questionList[qIndex].questionState;
        if (questionState != 'answered') {
            $scope.questionList[qIndex].chosenAnswer = aIndex;
        }
    };

    $scope.confirmAnswer = function(qIndex) {
        var qState = $scope.questionList[qIndex].questionState;

        var chosenAnswer = $scope.questionList[qIndex].chosenAnswer;
        if (chosenAnswer === undefined) {
            alert('You did not choose the answer!');
            return;
        }
        if (qState != 'answered') {
            if (chosenAnswer === $scope.questionList[qIndex].correctIndex){
                $scope.questionList[qIndex].correctness = 'correct';
                $scope.score++;
            } else {
                $scope.questionList[qIndex].correctness = 'incorrect';
            }
            $scope.questionList[qIndex].questionState = 'answered';
            $scope.questionsAnswered++;
            if ($scope.questionsAnswered == $scope.totalQuestions) {
                $scope.activeQuestion = $scope.totalQuestions + 1;
                $scope.finalize();
            }
        } else {
            alert('You cannot change your decision any more!')
        }

    };
        $scope.finalize = function() {
            $scope.countScore();
            prepareResults();
            sendResults()
        };

    $scope.countScore = function() {
        $scope.percentage = $scope.score / $scope.totalQuestions;
    };




    quizes.fetchQuizData(quizId).success(function(data) {
        $scope.quiz = data;
        $scope.quizName = data.description;
        $scope.timeLimit = data.timeLimit;
        $scope.questionList = data.questions;
        $scope.totalQuestions = $scope.questionList.length;
        for(var i = 0; i < $scope.questionList.length; i++) {
            $scope.questionList[i].questionState = 'unanswered';
        }

    })
        .error(function(err) {
            $scope.readError = err;
        });

    $scope.initCountDown = function() {
        var timerCount = $scope.timeLimit * 60;


        var countDown = function() {
            if (timerCount <= 0) {
                $scope.activeQuestion = $scope.totalQuestions + 1;
                $scope.countScore();
            } else {
                $scope.countDownLeft = timerCount;
                $scope.countMinutesLeft = Math.floor($scope.countDownLeft / 60);
                $scope.countSecondsLeft = $scope.countDownLeft % 60;
                    timerCount--;
                $timeout(countDown, 1000);
            }
        };
        $scope.countDownLeft = timerCount;
        countDown();
    };

        var prepareResults = function() {
            $scope.userQuiz.user = $scope.currentUser;
            $scope.userQuiz.quiz = $scope.quiz;
            $scope.userQuiz.score = $scope.percentage * 100;
            $scope.userQuiz.date = new Date();
        };

        var sendResults = function() {
            quizes.sendQuizData($scope.userQuiz)
                .success(function(data) {
                    if (data) {
                        $scope.message = 'Test data sent to server!';
                    } else {
                        $scope.message = '!!!!!!!!!??????';
                    }
                })
                .error(function(err) {
                    $scope.message = err;
                })
        };



    $scope.startQuiz = function() {
        $scope.activeQuestion = 0;
        if ($scope.timeLimit > 0) {
            $scope.initCountDown();
        }
    };

    $scope.toDashboard = function() {
        $location.path('/dashboard');
    }

}]);