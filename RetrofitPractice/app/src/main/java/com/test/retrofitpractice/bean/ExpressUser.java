package com.test.retrofitpractice.bean;

/**
 * Created by Administrator on 2017/1/20.
 */
public class ExpressUser {
    /**
     * info : {"uid":"31","pwd":"e10adc3949ba59abbe56e057f20f883e","state":"2","name":"廖欢","photo":"http://wxtest.kuaijiankang.com/assets/img/express/14005577264578.jpg","star":"3.4087"}
     */

    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public static class Info {
        /**
         * uid : 31
         * pwd : e10adc3949ba59abbe56e057f20f883e
         * state : 2
         * name : 廖欢
         * photo : http://wxtest.kuaijiankang.com/assets/img/express/14005577264578.jpg
         * star : 3.4087
         */

        private String uid;
        private String pwd;
        private String state;
        private String name;
        private String photo;
        private String star;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }
    }
}
