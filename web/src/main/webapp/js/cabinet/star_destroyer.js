/**
 * Created by al1 on 06.06.15.
 */
define(['star_destroyer'], function() {

    $(document).ready(function () {
        function wait(millis) {
            window.setTimeout(run, millis)
        }

        function run() {
            var battlestar = document.getElementById("battlestar");
            battlestar.style.display = 'block';
            move(-800, 0, 0.2, 0.1, battlestar);
        }

        function move(left, top, leftInc, topInc, logo) {
            var iniLeft = left;
            var iniTop = top;
            var iniTopInc = topInc;
            var iniLeftInc = leftInc;

            function frame() {
                left += leftInc;
                top = top + topInc;
                logo.style.left = Math.round(left) + 'px';
                logo.style.top = Math.round(top) + 'px';

                if (left > 2000) {
                    left = iniLeft;
                    top = iniTop;
                    leftInc = iniLeftInc;
                    topInc = iniTopInc;
                    clearInterval(timer);
                }
            }

            var timer = setInterval(frame, 20);
        }

        wait(Math.random() * 82800000 + 3600000); // 1-24h

    });
});
