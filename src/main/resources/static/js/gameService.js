var app = angular.module("GameApp", []);

app.controller("mainCtrl", function ($scope) {
    $scope.authoriz = "authorization.html";
    $scope.persarea = "personalArea.html";
    $scope.selectmenu = "selectMenu.html";
    $scope.gameField = "gameField.html";
    $scope.resultarea = "result.html";
    $scope.url1 = $scope.authoriz;


    ////////////////////// for sockets

    var socket;
    var client;

    $scope.connect = function () {
        socket = new SockJS('/brainWar');
        client = Stomp.over(socket);
        client.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            client.subscribe('/topic/response', function(message){
                console.log(JSON.parse(message.body).content);
            });
        });
    };

    $scope.sendInfo = function() {
        //client.send("/app/questions", {}, JSON.stringify({ 'email': $scope.email, 'login' : $scope.info.nickname }));
        //client.send("/app/take", {}, JSON.stringify({ 'email': $scope.email, 'login' : $scope.info.nickname }));
    };

    $scope.disconnect = function() {
        client.disconnect();
        console.log("Disconnected");
    };

    //////////////////////


    $scope.showAuthorization = function () {
        $scope.url1 = $scope.authoriz;
    };

    $scope.showPersonalArea = function () {
        $scope.url1 = $scope.persarea;
    };

    $scope.showSelectMenu = function () {
        $scope.url1 = $scope.selectmenu;
    };

    $scope.showGameField = function () {
        $scope.url1 = $scope.gameField;
    };

    $scope.showResultArea = function () {
        $scope.url1 = $scope.resultarea;
    };

    $scope.$on("messageEvent", function (event, args) {
        $scope.info = args.message;
    });

    $scope.$on("toGameField", function (event) {
        $scope.showGameField();
    });

    $scope.$on("questionData", function (event, args) {
        console.log(args.message);
        $scope.object = args.message;
    });

    $scope.$on("toPersonalArea", function (event) {
        $scope.showPersonalArea();
    });

    $scope.$on("toResultArea", function (event) {
        $scope.showResultArea();
    });

    $scope.$on("answerData1", function (event, args) {
        $scope.answerdata = args.message;
    });

    $scope.$on("answerData2", function (event, args) {
        $scope.answerpointdata = args.message;
    });

    $scope.$on("saveEmail", function (event, args) {
        $scope.email = args.message;
    });
});

app.controller("signUpServiceCtrl", function ($scope, $http) {
    $scope.sendRequest = function () {
        var email = document.getElementById('email').value;
        var login = document.getElementById('login').value;
        var password = document.getElementById('password').value;
        $scope.message = "";
        var promise = $http.post("http://localhost:8080/v1/signUp",
            JSON.stringify({'email' : email, 'nickname' : login, 'password' : password}));

        promise.then(successanswer, failanswer);

        function successanswer(response) {
            console.log("Status: " + response.status);
            var answer = response.data;
            if (answer == true) {
                $scope.message = "Регистрация прошла успешно, войдите в систему.";
            } else {
                $scope.message = "На данный email уже имеется зарегестрированный пользователь.";
            }
        }

        function failanswer() {
            console.log(error.status);
        }
    }

});

app.controller("loginServiceCtrl", function ($scope, $http) {
    $scope.sendRequest = function () {
        $scope.email = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        var promise = $http.post("http://localhost:8080/v1/login", JSON.stringify({ 'email': $scope.email, 'password' : password}));
        promise.then(fulfilled, rejected);
    };

    function fulfilled(response) {
        console.log("Status: " + response.status);
        $scope.items = response.data;

        if ($scope.items.contained == true) {
            $scope.showPersonalArea();
            $scope.$emit("messageEvent", {
                message: $scope.items
            });
            $scope.$emit("saveEmail", {
                message: $scope.email
            });
        } else {
            $scope.showAuthorization();
        }
    }

    function rejected(error) {
        console.log(error.status);
    }
});

app.controller("LoginSignupCtrl", function ($scope) {
    $scope.loginForm = "loginForm.html";
    $scope.signUpForm = "signUpForm.html";

    $scope.url2 = $scope.loginForm;

    $scope.showLogin = function () {
        $scope.url2 = $scope.loginForm;
    };

    $scope.showSignup = function () {
        $scope.url2 = $scope.signUpForm;
    };
});

app.controller("singleGameCtrl", function ($scope, $http) {
    $scope.sendRequest = function () {
        var theme = "theme";
        var promise = $http.post("http://localhost:8080/single/game", JSON.stringify({ 'theme': theme}));
        promise.then(fulfilled, rejected);

        function fulfilled(response) {
            $scope.questions = response.data;
            console.log(response.status);
            console.log(response.data);
            $scope.$emit("toGameField");
            $scope.$emit("questionData", {
                message: $scope.questions.questions
            });
        }

        function rejected(error) {
            console.log(error.status);
        }
    };
});
app.controller("gameCtrl", function ($scope, $interval) {
    $scope.addpoint = 0;
    $scope.result = [false, false, false];
    $scope.counter = 10;
    $scope.i = 0;
    $scope.$watch("counter", function () {
        if ($scope.counter == -1) {
            dropping();
        }
    });
    $interval(function () {
        $scope.counter--;
    }, 1000);

    $scope.giveAnswer = function (id) {
        var answer = "answer" + id;
        console.log(answer);
        switch (answer) {
            case "answer1":
                if ($scope.object[$scope.i].answer1 == $scope.object[$scope.i].trueanswer) $scope.result[$scope.i] = true;
                break;
            case "answer2":
                if ($scope.object[$scope.i].answer2 == $scope.object[$scope.i].trueanswer) $scope.result[$scope.i] = true;
                break;
            case "answer3":
                if ($scope.object[$scope.i].answer3 == $scope.object[$scope.i].trueanswer) $scope.result[$scope.i] = true;
                break;
            case "answer4":
                if ($scope.object[$scope.i].answer4 == $scope.object[$scope.i].trueanswer) $scope.result[$scope.i] = true;
                break;
        }
        dropping();
    };

    var dropping = function () {
        $scope.i++;
        $scope.counter = 10;
        if ($scope.i > 2) {
            getResult();
            $scope.$emit("toResultArea");
        }
    };

    var getResult = function () {
        for (var j = 0; j < $scope.result.length; j++) {
            if ($scope.result[j]) {
                $scope.addpoint += 5;
                $scope.result[j] = "Верно!"
            } else {
                $scope.result[j] = "Неверно."
            }
        }

        $scope.$emit("answerData1", {
            message: $scope.result
        });

        $scope.$emit("answerData2", {
            message: $scope.addpoint
        });
    }
});

app.controller("resultCtrl", function ($scope, $http) {
    $scope.sendResultOfGame = function () {
        var email = $scope.email;
        var addpoints = $scope.answerpointdata;
        var promise = $http.post("http://localhost:8080/single/profile", JSON.stringify({ 'email': email,
            'newPoints' : addpoints}));
        promise.then(fulfilled, rejected);

        function fulfilled(response) {
            $scope.page = response.data;
            console.log(response.status);
            console.log(response.data);
            $scope.$emit("messageEvent", {
                message: $scope.page
            });
            $scope.$emit("toPersonalArea");
        }

        function rejected(error) {
            console.log(error.status);
        }
    };
});