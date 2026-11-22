-- ----------------------------
-- 业务模块菜单与按钮权限扩展
-- 覆盖：设备插槽、订单账单/日志/统计、支付信息、用户登录日志、AI统计
-- 适用：已执行 share-system.sql 的环境，执行一次即可
-- ----------------------------

-- 支付管理
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2100, '支付管理', 0, 6, 'payment', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'money', 'admin', NOW(), '', NULL, '支付管理目录'),
(2101, '支付信息', 2100, 1, 'paymentInfo', 'payment/paymentInfo/index', NULL, 1, 0, 'C', '0', '0', 'payment:paymentInfo:list', 'documentation', 'admin', NOW(), '', NULL, '支付信息菜单'),
(2102, '支付信息查询', 2101, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'payment:paymentInfo:query', '#', 'admin', NOW(), '', NULL, ''),
(2103, '支付信息新增', 2101, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'payment:paymentInfo:add', '#', 'admin', NOW(), '', NULL, ''),
(2104, '支付信息修改', 2101, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'payment:paymentInfo:edit', '#', 'admin', NOW(), '', NULL, ''),
(2105, '支付信息删除', 2101, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'payment:paymentInfo:remove', '#', 'admin', NOW(), '', NULL, ''),
(2130, '支付信息导出', 2101, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'payment:paymentInfo:export', '#', 'admin', NOW(), '', NULL, '');

-- 设备管理 -> 柜机插槽
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2106, '柜机插槽', 2006, 5, 'cabinetSlot', 'device/cabinetSlot/index', NULL, 1, 0, 'C', '0', '0', 'device:cabinetSlot:list', 'component', 'admin', NOW(), '', NULL, '柜机插槽菜单'),
(2107, '柜机插槽查询', 2106, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'device:cabinetSlot:query', '#', 'admin', NOW(), '', NULL, ''),
(2108, '柜机插槽新增', 2106, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'device:cabinetSlot:add', '#', 'admin', NOW(), '', NULL, ''),
(2109, '柜机插槽修改', 2106, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'device:cabinetSlot:edit', '#', 'admin', NOW(), '', NULL, ''),
(2110, '柜机插槽删除', 2106, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'device:cabinetSlot:remove', '#', 'admin', NOW(), '', NULL, '');

-- 订单管理 -> 订单账单 / 订单日志 / 订单统计
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2111, '订单账单', 2044, 2, 'orderBill', 'order/orderBill/index', NULL, 1, 0, 'C', '0', '0', 'order:orderBill:list', 'form', 'admin', NOW(), '', NULL, '订单账单菜单'),
(2112, '订单账单查询', 2111, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderBill:query', '#', 'admin', NOW(), '', NULL, ''),
(2113, '订单账单新增', 2111, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderBill:add', '#', 'admin', NOW(), '', NULL, ''),
(2114, '订单账单修改', 2111, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderBill:edit', '#', 'admin', NOW(), '', NULL, ''),
(2115, '订单账单删除', 2111, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderBill:remove', '#', 'admin', NOW(), '', NULL, ''),
(2116, '订单账单导出', 2111, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderBill:export', '#', 'admin', NOW(), '', NULL, ''),
(2117, '订单日志', 2044, 3, 'orderLog', 'order/orderLog/index', NULL, 1, 0, 'C', '0', '0', 'order:orderLog:list', 'log', 'admin', NOW(), '', NULL, '订单日志菜单'),
(2118, '订单日志查询', 2117, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderLog:query', '#', 'admin', NOW(), '', NULL, ''),
(2119, '订单日志新增', 2117, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderLog:add', '#', 'admin', NOW(), '', NULL, ''),
(2120, '订单日志删除', 2117, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderLog:remove', '#', 'admin', NOW(), '', NULL, ''),
(2121, '订单日志导出', 2117, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderLog:export', '#', 'admin', NOW(), '', NULL, ''),
(2122, '订单统计', 2044, 4, 'orderStatistics', 'order/orderStatistics/index', NULL, 1, 0, 'C', '0', '0', 'order:orderStatistics:list', 'chart', 'admin', NOW(), '', NULL, '订单统计菜单'),
(2123, '订单统计刷新', 2122, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'order:orderStatistics:refresh', '#', 'admin', NOW(), '', NULL, '');

-- 会员管理 -> 用户登录日志
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2124, '用户登录日志', 2055, 3, 'userLoginLog', 'user/userLoginLog/index', NULL, 1, 0, 'C', '0', '0', 'user:userLoginLog:list', 'logininfor', 'admin', NOW(), '', NULL, '用户登录日志菜单'),
(2125, '登录日志查询', 2124, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'user:userLoginLog:query', '#', 'admin', NOW(), '', NULL, ''),
(2126, '登录日志删除', 2124, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'user:userLoginLog:remove', '#', 'admin', NOW(), '', NULL, ''),
(2127, '登录日志导出', 2124, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'user:userLoginLog:export', '#', 'admin', NOW(), '', NULL, '');

-- AI 报表按钮权限
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES
(2128, '订单数据查询', 2061, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'stastics:orderData:list', '#', 'admin', NOW(), '', NULL, ''),
(2129, '用户数据查询', 2060, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'stastics:userData:list', '#', 'admin', NOW(), '', NULL, '');
