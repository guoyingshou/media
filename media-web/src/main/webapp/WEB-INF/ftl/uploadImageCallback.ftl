<html>
<body>
    <script type='text/javascript'>
    <#--
        window.parent.CKEDITOR.tools.callFunction("${num}","${imageUrl}", "${msg!''}");
        -->
        window.opener.CKEDITOR.tools.callFunction("${num}","${imageUrl}");
    </script>
</body>
</html>
