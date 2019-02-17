<#import "parts/pageTempl.ftl" as body>

<@body.page>
    <a href="/posts/clear" class="btn btn-outline-dark btn-sm">Clear Images</a>

    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="false">
        Search by tag
    </button>

    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseInput" aria-expanded="false">
        Post
    </button>
    <div class="collapse" id="collapseFilter">
        <form action="/posts" method="get" class="my-3">
            <div class="form-group">
                <input type="text" placeholder="Write filter tag" name="tag" class="form-control">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-dark btn-block">Submit</button>
            </div>
        </form>
    </div>

    <div class="collapse" id="collapseInput">
    <form action="/posts" method="post" enctype="multipart/form-data" class="my-3">
        <div class="form-group">
            <input type="text" placeholder="Image title" name="title" class="form-control">
        </div>
        <div class="form-group">
            <input type="text" placeholder="Image tag" name="tag" class="form-control">
        </div>
        <div class="input-group mb-3">
            <div class="custom-file">
                <input type="file" name="file" class="custom-file-input" id="inputFile">
                <label class="custom-file-label" for="inputFile">Choose file</label>
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-secondary btn-block">Submit</button>
        </div>
    </form>
    </div>

    <#if posts??>
        <div class="card-columns">
    <#list posts as post>
        <div class="card text-white bg-secondary my-2">
            <#if post.imageName??>
                <img src="/img/${post.tag.text}/${post.imageName}" class="card-img-top">
            </#if>
            <div class="card-body">
                <h5 class="card-title">${post.title}</h5>
                <p class="card-text">${post.tag.text}</p>
            </div>
        </div>
    </#list>
        </div>
    </#if>
</@body.page>