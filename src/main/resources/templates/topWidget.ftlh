<div class="card my-4">
    <h5 class="card-header">Топ публікацій</h5>
    <div class="card-body">
        <table>
            <#list topPost as post>
                <tr>
                    <td>${post_index+1}.</td>
                    <td><a href="/post/${post.id?c}">${post.title}</a></td>
                    <td>${post.views}</td>
                </tr>
            </#list>
        </table>
    </div>
</div>