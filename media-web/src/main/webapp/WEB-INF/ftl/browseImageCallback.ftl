<!DOCTYPE html>
<html>
<head>
    <script type='text/javascript' src='/jquery/jquery-1.8.3.min.js'></script>
    <script type='text/javascript'>
        var num = ${num};
        $(document).ready(function() {
            $('a').on('click', function(e) {
                e.preventDefault();
                window.opener.CKEDITOR.tools.callFunction(num, '/images/723.jpg');
                window.close();
            });

        });
    </script>
</head>
<body>
    test image browse...
    <hr/>
    <a href="#" title="fjda">choose</a>
</body>
</html>
