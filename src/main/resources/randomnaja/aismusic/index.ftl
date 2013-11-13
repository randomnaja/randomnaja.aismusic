<#-- @ftlvariable name="contents" type="java.util.List<randomnaja.aismusic.xmlpojo.Content>" -->
<#setting url_escaping_charset='UTF-8'>
<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 50px;
            padding-bottom: 20px;
        }
    </style>
    <link rel="stylesheet" href="/assets/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/assets/css/main.css">

    <script src="/assets/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.html">randomnaja.AIS Music Store</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.html">Home</a></li>
            </ul>
        </div><!--/.navbar-collapse -->
    </div>
</div>

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
    <div class="container">
        <h1>My AIS Music Store</h1>
        <p>Search songs from AIS Music Store</p>

        <form method="GET">
            Keyword : <input type="text" name="keyword" value="${ (keyword!"")?html }" />
            <input type="submit" value="Submit" />
        </form>
    </div>
</div>

<div class="container">
    <#if contents??>
        <#list contents as each>
            <div class="row">
                <div class="col-md-3">
                    <img src="${each.cover}" alt="Album Cover of ${each.artistTH?html}" class="img-thumbnail" />
                </div>
                <div class="col-md-9">
                    <form class="form-horizontal" role="form" action="download.html" method="post" target="_blank">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">GMMDCode</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">${each.gmmdCode !}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">CP_ID</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">${each.cpId !}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">
                                    ${each.songNameTH !} / ${each.songNameEN !}
                                </p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Album/Artist</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">
                                    ${each.albumTH !} : ${each.artistTH !}
                                </p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Sample song</label>
                            <div class="col-sm-10">
                                <p class="form-control-static">
                                    <a class="media" href="${each.preview}">Click to listen</a>
                                </p>
                            </div>
                        </div>
                        <input type="hidden" name="gmmdCode" value="${each.gmmdCode !}" />
                        <input type="hidden" name="cpId" value="${each.cpId !}" />
                        <input type="hidden" name="fileName" value="${(each.songNameTH !)?url}%20-%20${(each.artistTH !)?url}.mp3" />
                        <button type="submit" class="btn btn-default">Download</button>
                    </form>
                </div>
            </div>
        </#list>
    </#if>

    <hr>

    <footer>
        <p>&copy; Company 2013</p>
    </footer>
</div> <!-- /container -->        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/assets/js/vendor/jquery-1.10.1.js"><\/script>')</script>

<script src="/assets/js/vendor/bootstrap.min.js"></script>

<script src="/assets/js/main.js"></script>
<script src="/assets/js/vendor/soundmanager2.js"></script>

<script>
$(function(){
    $('.media').bind("click", function(evt){
        soundManager.destroySound('sampleSong');
        var ln = $(this).attr('href');
        soundManager.play('sampleSong', ln);
        evt.preventDefault();
    });
});
soundManager.setup({
    url: '/assets/swf/soundmanager2_flash9.swf',
    flashVersion: 9, // optional: shiny features (default = 8)
    // optional: ignore Flash where possible, use 100% HTML5 mode
    // preferFlash: false,
    onready: function() {
        // Ready to use; soundManager.createSound() etc. can now be called.
    }
});
</script>

</body>
</html>

