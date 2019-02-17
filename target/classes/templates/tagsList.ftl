<#import "parts/pageTempl.ftl" as body>

<@body.page>
    <#if tags??>
        <div class="card-columns">
            <#list tags as tag>
                <div class="card my-1">
                    <div class="card-body">
                        <p class="card-text"><a href="/posts?tag=${tag.text}">${tag.text}</a></p>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</@body.page>