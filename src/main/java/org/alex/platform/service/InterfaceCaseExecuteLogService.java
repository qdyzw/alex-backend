package org.alex.platform.service;

import com.github.pagehelper.PageInfo;
import org.alex.platform.pojo.InterfaceCaseExecuteLogDO;
import org.alex.platform.pojo.InterfaceCaseExecuteLogListDTO;
import org.alex.platform.pojo.InterfaceCaseExecuteLogListVO;
import org.alex.platform.pojo.InterfaceCaseExecuteLogVO;

import java.util.List;

public interface InterfaceCaseExecuteLogService {
    void saveExecuteLog(InterfaceCaseExecuteLogDO executeLogDO);
    PageInfo<InterfaceCaseExecuteLogListVO> findExecuteList(InterfaceCaseExecuteLogListDTO executeLogListDTO,
                                                            Integer pageNum, Integer pageSize);
    InterfaceCaseExecuteLogVO findExecute(Integer executeId);
}
