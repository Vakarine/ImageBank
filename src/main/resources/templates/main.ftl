<#import "parts/pageTempl.ftl" as body>

<@body.page>
    <form action="/posts" method="post" enctype="multipart/form-data">
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
            <button type="submit" class="btn btn-secondary">Submit</button>
        </div>
    </form>

    <#if posts??>
    <#list posts as post>
        <div class="card text-white bg-secondary my-3">
            <img src="/img/${post.imageName}" class="card-img-top">
            <div class="card-body">
                <h5 class="card-title">${post.title}</h5>
                <p class="card-text">${post.tag}</p>
            </div>
        </div>
    </#list>
    </#if>
</@body.page>