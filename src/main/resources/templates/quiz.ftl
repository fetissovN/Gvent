<html>
<head>
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/foundation.min.css">
    <link rel="stylesheet" type="text/css" href="/css/app.css">
    <script type="text/javascript" src="/js/foundation.min.js"></script>
    <script type="text/javascript" src="/js/quizPage.js"></script>
</head>
<body>
<div class="row">
    <h1 align="center">Voter</h1>
    <div class="large-3 column">
        <a href="/"><button id="back" class="button">Back to main page</button></a><br>
    </div>
    <div class="large-6 large-centered column">
        <article class="article-row">
            <div class="article-row-content">
                <p class="article-row-content-header">Id ${quiz.id}</p>
                <p class="article-row-content-description">${quiz.theme}</p>
                <#if quiz.started == 1 && quiz.closed == 0 >
                    <p id="voteTag" class="article-row-content-author">
                        Votes: ${quiz.votes}
                        <a id="vote" class="${quiz.id}.${quiz.started}.${quiz.closed}">Click to vote!</a>
                    </p>
                </#if>
            </div>
        </article>
        <#if quiz.started == 0>
            <button id="toStart" name="${quiz.id}" class="button">Start!</button>
        <#elseif quiz.closed == 0>
            <button id="toClose" name="${quiz.id}" class="button">Close!</button>
        </#if>
    </div>
    <div class="large-3 column"></div>
</div>
</body>
</html>
