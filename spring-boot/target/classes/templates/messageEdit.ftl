<#import "parts/common.ftl" as c>

<@c.page>
Message editor

<form action = "/message" method="post">
<input type="text" name="text" value="${message.text}">
<#list statuses as status>
<div>
	<label><input type="checkbox" name = "${status}" ${message.statuses?seq_contains(status)?string("checked", "")}>${status}</label>
</div>
</#list>
   <input type="hidden" value="${message.id}" name="messageId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
<button type="submit">Save</button>
</form>
</@c.page>