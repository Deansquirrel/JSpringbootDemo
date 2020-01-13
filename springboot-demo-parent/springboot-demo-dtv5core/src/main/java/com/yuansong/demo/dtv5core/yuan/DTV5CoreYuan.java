package com.yuansong.demo.dtv5core.yuan;

import java.util.Collection;

public interface DTV5CoreYuan {

	/**
	 * 获取一个事件存根号
	 * 
	 * @return
	 */
	public String GetNewShijCungno();
	
	/**
	 * 请求发送操作Offer
	 * @param pmChan 传播模式
	 * @param pmMubXitLeix 目标系统类型
	 * @param pmMubXitID 目标系统ID
	 * @throws Exception
	 */
	public void RequestSendOffer(String pmChan, String pmMubXitLeix, long pmMubXitID) throws Exception;

	/**
	 * 释放发送操作Offer
	 * @param pmChan 传播模式
	 * @param pmMubXitLeix 目标系统类型
	 * @param pmMubXitID 目标系统ID
	 * @throws Exception
	 */
	public void ReleaseSendOffer(String pmChan, String pmMubXitLeix, long pmMubXitID) throws Exception;
	
	/**
	 * 编队指定传播路径的新事件
	 * @param pmChan 传播模式
	 * @param pmMubXitLeix 目标系统类型
	 * @param pmMubXitID 目标系统ID
	 * @param pmJiezhShijCungno 本次操作的截止事件存根号。如果不存在，则所有。
	 */
	public void BiandShij(String pmChan, String pmMubXitLeix, long pmMubXitID, String pmJiezhShijCungno);
	
	/**
	 * 获取DTCH传出目标路径清单
	 * [待修改] private 返回 Collection
	 * 返回：包含路径清单的一个记录集。字段如下
	 * 	yuanxitleix nvarchar(255)
	 * 	yuanxitid int
	 * chan varchar(20)
	 * mubxitleix nvarchar(255)
	 * mubxitid int
	 * 
	 * @param pmTongdIndex
	 */
	public void GetDTCHMubList(long pmTongdIndex);
	
	/**
	 * DTCH方式发送指定传播路径的已编队事件
	 * [待修改] 参数缺少回调 ByVal pmCallback As ICallbackSendonDTCH
	 * 
	 * 过程
	 * 申请源端Offer(传播路径,"发送")
	 * 一次性读取所有需要传播的事件信息（orderby 编队号）,同时读取相关的消息。
	 * Loop Begin：每次一个事件
	 * 		检查通道Offer
	 * 			false 申请通道Offer
	 * 		提交事件到通道库
	 * 		标记该事件为成功发送状态
	 * Loop End
	 * 检查通道Offer是否存在
	 * 		true 释放通道Offer
	 * 释放源端Offer(传播路径,"发送")
	 * 
	 * @param pmChan 传播模式
	 * @param pmMubXitLeix 目标系统类型
	 * @param pmMubXitID 目标系统ID
	 * @param pmTongdIndex 通道库索引
	 */
	public void SendOnDTCH(String pmChan, String pmMubXitLeix, long pmMubXitID, long pmTongdIndex);
	
	/**
	 * 读取当前一批待传播数据（事件+消息，单批有限量）
	 * [待修改] private 返回 Collection
	 * 
	 * @param pmChan
	 * @param pmMubXitLeix
	 * @param pmMubXitID
	 * @throws Exception
	 */
	void ReadPreSendBatch(String pmChan, String pmMubXitLeix, long pmMubXitID) throws Exception; 
	
	/**
	 * 标记成功发送
	 * [待修改] private 
	 * 
	 * @param pmChan 传播模式
	 * @param pmMubXitLeix 目标系统类型
	 * @param pmMubXitID 目标系统ID
	 * @param pmChuanbSN 发送成功的传播编队号
	 * @param pmShijCungno 发送成功的传播编队号对应的事件存根号
	 * @param pmFasTime 发送时间(String * 23,yyyy-MM-dd Hh:Nn:Ss.mmm)
	 */
	void SetSendSuccess(String pmChan, String pmMubXitLeix, long pmMubXitID, String pmChuanbSN, String pmShijCungno, String pmFasTime);
	
	/**
	 * 重设成功传播状态sn
	 * [待修改] private
	 * 
	 * @param pmChan 传播模式
	 * @param pmMubXitLeix 目标系统类型
	 * @param pmMubXitID 目标系统ID
	 * @param pmChuanbSN 目标系统成功恢复的最后传播SN
	 * @param pmShijCungno 目标系统成功恢复的最后传播SN对应的事件存根号
	 */
	void ResetSendSuccess(String pmChan, String pmMubXitLeix, long pmMubXitID, String pmChuanbSN, String pmShijCungno);
	
	/**
	 * 提交单个事件到目标端
	 * [待修改] private
	 * [待修改] Collection 修改为具体类型
	 * [待修改] 参数缺少 ByVal pmTongdSubmitCmd As ADODB.Command
	 * 
	 * @param pmShijRs 包含当前需要提交的事件的事件清单
	 * @param pmXiaoxRs 包含当前需要提交的事件的消息清单
	 * @param pmFasTime 发送时间(String * 23,yyyy-MM-dd Hh:Nn:Ss.mmm)
	 */
	void SubmitXiaoxPackTongd(Collection<?> pmShijRs, Collection<?> pmXiaoxRs, String pmFasTime); 
	
	/**
	 * 在通道申请一个指定的DTCH发送操作锁
	 * [待修改] 参数缺少 ByVal pmTongdConn As ADODB.Connection
	 * 
	 * @param pmChan
	 * @param pmMubXitLeix
	 * @param pmMubXitID
	 * @param pmLocalTime
	 */
	void RequestSendOfferTongdDTCH(String pmChan, String pmMubXitLeix, long pmMubXitID, String pmLocalTime);
	
	/**
	 * 在通道释放一个指定的DTCH发送操作锁
	 * [待修改] 参数缺少 ByVal pmTongdConn As ADODB.Connection
	 * 
	 * @param pmChan
	 * @param pmMubXitLeix
	 * @param pmMubXitID
	 */
	void ReleaseSendOfferTongdDTCH(String pmChan, String pmMubXitLeix, long pmMubXitID);
	
//	'连接指定索引值的DTCH通道数据库
//	'输入：
//	'pmTongdIndex：通道库索引，从0到Count-1
//	'CSEH: ErrRaise
//	Private Function ConnectTongd(ByVal pmTongdIndex As Long) As ADODB.Connection
//	        '<EhHeader>
//	        On Error GoTo errHandler
//	        '</EhHeader>
//	        Dim tTongdConnParams() As String
//	        Dim ret As ADODB.Connection
//	    
//	101     If Not (pmTongdIndex >= 0 And pmTongdIndex <= GVAR.TongdCount - 1) Then
//	103         Err.Raise vbObjectError, , "通道库索引无效。"
//	        End If
//	105     tTongdConnParams = Split(GVAR.FindParam("tongd:" & CStr(pmTongdIndex)), "|")
//	107     If UBound(tTongdConnParams) = 1 Then
//	109         tTongdConnParams = Split(mdlCrypto.DecryptFromBase64Format(tTongdConnParams(1), tTongdConnParams(0)), "|")
//	111         If UBound(tTongdConnParams) <> 3 Then
//	113             Err.Raise vbObjectError, , "通道库参数无效。（" & CStr(pmTongdIndex) & "）"
//	            End If
//	        Else
//	115         Err.Raise vbObjectError, , "通道库参数无效。（" & CStr(pmTongdIndex) & "）"
//	        End If
//	117     Set ret = ConstMSSQL.GetConnection(tTongdConnParams(0), tTongdConnParams(1), tTongdConnParams(2), tTongdConnParams(3), 10)
//	    
//	118     ret.CommandTimeout = 0
//	    
//	119     Set ConnectTongd = ret
//	        '<EhFooter>
//	        Exit Function
//	        Resume
//	errHandler:
//	        Call gEx.SetSysErr(Err, Erl(), "Service", "ConnectTongd")
//	        Resume errProc
//	errProc:
//	        On Error Resume Next
//	        '在此处添加错误后的恢复代码，例如：关闭打开的数据集，回滚数据库事务，恢复已经操作的模块、全局变量
//	    
//	        On Error GoTo 0
//	        Call gEx.RaiseErr '/RaiseErr 如果当前过程是内部过程，请替换ShowErr
//	        '</EhFooter>
//	End Function
	
	
//	'创建DTCH发送操作的消息提交命令ADODB.Command
//	'返回：消息提交命令。其中params参数中key含义如下：
//	'    chuanbqishsn：本批传播数据包的起始序号
//	'    chuanbzhongzhsn：本批传播数据包的终止序号
//	'    fastimeyuan：本次传播源本地操作时间
//	'    xiaoxpack：本次传播的数据包文件
//	'CSEH: ErrRaise
//	Private Function BuildSendCmdTongdDTCH(ByVal pmChan As String, ByVal pmMubXitLeix As String, ByVal pmMubXitID As Long, ByVal pmMubChsRcv As Byte, ByVal pmTongdConn As ADODB.Connection) As ADODB.Command
//	    '<EhHeader>
//	    On Error GoTo errHandler
//	    '</EhHeader>
//	    Dim ret As ADODB.Command
//	    
//	    Set ret = ConstMSSQL.GetCommand(pmTongdConn)
//	    With ret
//
//	        .CommandType = adCmdStoredProc
//	        .NamedParameters = True
//	        .CommandText = "pr_dtv5dtchlujxiaox_insert"
//	        .Parameters.Refresh
//	        .Parameters.Item("@chancode").Value = GVAR.TongdChanCode
//	        .Parameters.Item("@yuanxitleix").Value = GVAR.XitLeix
//	        .Parameters.Item("@yuanxitid").Value = GVAR.XitID
//	        .Parameters.Item("@chan").Value = pmChan
//	        .Parameters.Item("@mubxitleix").Value = pmMubXitLeix
//	        .Parameters.Item("@mubxitid").Value = pmMubXitID
//	        .Parameters.Item("@yuanxitvstr").Value = GVAR.XitVStr
//	    End With
//	    Set BuildSendCmdTongdDTCH = ret
//	    '<EhFooter>
//	    Exit Function
//	    Resume
//	errHandler:
//	    Call gEx.SetSysErr(Err, Erl(), "Service", "BuildSendCmdTongdDTCH")
//	    Resume errProc
//	errProc:
//	    On Error Resume Next
//	    '在此处添加错误后的恢复代码，例如：关闭打开的数据集，回滚数据库事务，恢复已经操作的模块、全局变量
//	    
//	    On Error GoTo 0
//	    Call gEx.RaiseErr '/RaiseErr 如果当前过程是内部过程，请替换ShowErr
//	    '</EhFooter>
//	End Function
	

//'准备指定信道累计数据包
//'CSEH: ErrRaise
//Public Sub PrepareLeijPack(ByVal pmChan As String, ByVal pmMubXitLeix As String, ByVal pmMubXitID As Long)
//        '<EhHeader>
//        On Error GoTo errHandler
//        '</EhHeader>
//        Dim rc As Collection, ps() As String, fes As String, fret As Long
//
//101     Call FindChanName(pmChan)
//        
//103     Set rc = GVAR.DBDA.RequestModelStructure("dtv5yuanleij")
//    
//105     ReDim ps(0 To 2)
//107     ps(0) = "chan=" & pmChan
//109     ps(1) = "mubxitleix=" & pmMubXitLeix
//111     ps(2) = "mubxitid=" & pmMubXitID
//    
//113     fret = GVAR.DBDA.FireModelCommand("dtv5yuanleij", "update", Join(ps, GVAR.DASepratorCMDParamStr), rc, Nothing, fes)
//115     Select Case fret
//            Case 0, 1
//        
//117         Case 2
//119             Err.Raise vbObjectError, , "数据访问故障，请重试操作。（ret = " & fret & "）"
//121         Case Else
//123             Err.Raise vbObjectError, , "数据访问故障，请重试操作。（ret = " & fret & "）"
//        End Select
//        '<EhFooter>
//        Exit Sub
//        Resume
//errHandler:
//        Call gEx.SetSysErr(Err, Erl(), "Service", "PrepareLeijPack", "执行PrepareLeijPack出现错误。(YuanXitLeix=" & GVAR.XitLeix & ",YuanXitID=" & GVAR.XitID & ",Chan=" & pmChan & ",MubXitLeix=" & pmMubXitLeix & ",MubXitID=" & pmMubXitID & ")")
//        Resume errProc
//errProc:
//        On Error Resume Next
//        '在此处添加错误后的恢复代码，例如：关闭打开的数据集，回滚数据库事务，恢复已经操作的模块、全局变量
//    
//        On Error GoTo 0
//        Call gEx.RaiseErr '/RaiseErr 如果当前过程是内部过程，请替换ShowErr
//        '</EhFooter>
//End Sub
}
