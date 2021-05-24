<div class="card my-4">
    <h5 class="card-header">Топ коментарів</h5>
    <div class="card-body">
        <table>
            <#list topComments as comm>
                <tr>
                    <td title="${comm.authorComment} оставил отзыв ${comm.created}">
                        <span class="text-muted">~</span><a href="/post/${comm.postId?c}">${comm.comment}</a></td>
                    <td><span class="badge badge-success">${comm.likes}</span></td>
                </tr>
            </#list>
        </table>
    </div>
</div>