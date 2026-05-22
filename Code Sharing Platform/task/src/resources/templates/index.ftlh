<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/css/theme.css">
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<span id="load_date">${date}</span>
<pre id="code_snippet"><code><span>${code}</span></code></pre>

<#if time_restriction gt 0>
    <span id="time_restriction">${time_restriction}</span>
</#if>
<#if views_restriction gte 0 && views_initial_restriction gt 0>
    <span id="views_restriction">${views_restriction}</span>
</#if>
</body>
</html>
