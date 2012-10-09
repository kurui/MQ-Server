package com.kurui.kums.message.biz;

import java.util.List;
import com.kurui.kums.message.MessageListForm;
import com.kurui.kums.base.exception.AppException;

public interface MessageBiz {

	public List list(MessageListForm agentListForm) throws AppException;

}
