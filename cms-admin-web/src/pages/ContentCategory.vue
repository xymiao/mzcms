<template>
   <el-breadcrumb separator="/">
   <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
   <el-breadcrumb-item><a href="/">内容分类管理</a></el-breadcrumb-item>
 </el-breadcrumb>
  <el-card class="box-card">
    <el-row>
      <el-col :span="4">
        <h5>父分类</h5>
        <el-tree
            :data="data"
            :props="defaultProps"
            @node-click="handleNodeClick" style="border-right: 1px #ccc solid;"
        ></el-tree>

      </el-col>
      <el-col :span="20">
        <template #header>
          <div class="card-header">
            <span>卡片名称</span>
            <el-button class="button" type="text">操作按钮</el-button>
          </div>
        </template>
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column prop="id" label="ID" width="80"></el-table-column>
          <el-table-column prop="name" label="名称" width="180"></el-table-column>
          <el-table-column prop="address" label="编码"></el-table-column>
          <el-table-column prop="address" label="父类类型"></el-table-column>
          <el-table-column prop="address" label="父分类"></el-table-column>
          <el-table-column prop="date" label="每页内容数"></el-table-column>
          <el-table-column prop="address" label="前台显示"></el-table-column>
          <el-table-column prop="address" label="排序"></el-table-column>
           <el-table-column fixed="right" label="操作" width="160">
                <template #default="scope">
                  <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
                    >编辑</el-button
                  >
                  <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)"
                    >删除</el-button
                  >
                </template>
              </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              v-model:currentPage="currentPage1"
              :page-size="100"
              layout="total, prev, pager, next"
              :total="1000"
          >
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  data() {
    return {

      data: [
        {
          label: '一级 1',
          children: [
            {
              label: '二级 1-1',
              children: [
                {
                  label: '三级 1-1-1',
                },
              ],
            },
          ],
        },
        {
          label: '一级 2',
          children: [
            {
              label: '二级 2-1',
              children: [
                {
                  label: '三级 2-1-1',
                },
              ],
            },
            {
              label: '二级 2-2',
              children: [
                {
                  label: '三级 2-2-1',
                },
              ],
            },
          ],
        },
        {
          label: '一级 3',
          children: [
            {
              label: '二级 3-1',
              children: [
                {
                  label: '三级 3-1-1',
                },
              ],
            },
            {
              label: '二级 3-2',
              children: [
                {
                  label: '三级 3-2-1',
                },
              ],
            },
          ],
        },
      ],

      defaultProps: {
        children: 'children',
        label: 'label',
      },

      tableData: [
        {
          id: '1',
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
        },
        {
          id: '2',
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄',
        },
        {
          id: '3',
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄',
        },
        {
          id: '4',
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄',
        },
      ],
    }
  },
  methods: {
    goBack() {
      console.log("go back")
    },
    handleNodeClick(data) {
      console.log(data)
    },
  }
}
</script>

<style>

</style>