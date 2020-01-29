<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/latte.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<link rel="stylesheet" type="text/css" href="./css/input.css">
<link rel="stylesheet" type="text/css" href="./css/message.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<title>決済確認画面</title>
<script type="text/javascript">
	function submitAction(url) {
		$('#ok').attr('action', url);
		$('#ok').submit();
	}
</script>
</head>
<body>

	<div id="header">
		<jsp:include page="header.jsp" />
	</div>

	<div id="contents">
		<h1>決済確認画面</h1>
		<div>
			<s:if test="destinationInfoDTOList.size() == 0">
				<div class="error">宛先情報がありません。</div>
			</s:if>
			<s:else>
				<div class="error">宛先情報を選択してください。</div>
				<s:form id="ok">
					<table class="r-table">
						<thead>
							<tr>
								<th><s:label value="#" /></th>
								<th><s:label value="姓" /></th>
								<th><s:label value="名" /></th>
								<th><s:label value="ふりがな" /></th>
								<th><s:label value="住所" /></th>
								<th><s:label value="電話番号" /></th>
								<th><s:label value="メールアドレス" /></th>
							</tr>
						</thead>

						<tbody>
							<s:iterator value="destinationInfoDTOList" status="st">

								<tr>
									<td>
										<s:if test="#st.index == 0">
											<input type="radio" name="id" checked="checked" value="<s:property value='id'/>" />
										</s:if>
										<s:else>
											<input type="radio" name="id" value="<s:property value='id'/>" />
										</s:else>
									</td>
									<td><s:property value="familyName" /></td>
									<td><s:property value="firstName" /></td>
									<td><s:property value="familyNameKana" /><span> </span><s:property value="firstNameKana" /></td>
									<td><s:property value="userAddress" /></td>
									<td><s:property value="telNumber" /></td>
									<td><s:property value="email" /></td>
								</tr>
							</s:iterator>
						</tbody>

					</table>
					<div class="submit_btn_box">
						<input type="button" value="削除" class="submit_btn" onclick="submitAction('DeleteDestinationAction')" />
							<br>
						<input type="button" value="決済" class="submit_btn" onclick="submitAction('SettlementCompleteAction')" />
					</div>
				</s:form>

			</s:else>
			<div class="submit_btn_box">
				<s:form action="CreateDestinationAction">
					<s:submit value="新規宛先登録" class="submit_btn" />
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>