<div class="card my-4">
    <h5 class="card-header">Categories</h5>
    <div class="card-body">
        <div class="row">
            <div class="col-lg-6">
                <ul class="list-unstyled mb-0">
                    <#list categoriesToHTML as cat>
                        <li>
                            <a href="/search/category/${cat.id}">${cat.name}</a>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>