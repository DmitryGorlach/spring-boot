<#import "parts/common.ftl" as c>

<@c.page>
List of messages
<table>
<thead>
	<tr>
		<th>Message</th>
		<th>Status</th>
		<th></th>
	</tr>
</thead>

<tbody>
<#list messages as message>
	<tr>
		<td>${message.text}</td>
		<td><#list message.statuses as status>${status}<#sep>, </#list></td>
		<td><a href="/message/${message.id}">edit</a></td>
	</tr>
</#list>
</tbody>
</table>

<span><a href="/main">Back to main</a></span>
</@c.page>
