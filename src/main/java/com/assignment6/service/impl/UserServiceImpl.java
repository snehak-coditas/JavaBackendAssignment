package com.assignment6.service.impl;

import com.assignment6.service.UserService;
import com.assignment6.service.dto.ItemsDTO;
import com.assignment6.service.dto.UserDetailsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserDetailsDTO searchUsers(String search) {

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpGet httpGet = new HttpGet("https://api.github.com/search/users?q="+search);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse;
        try {

            httpResponse = httpClient.execute(httpGet);
            Map<String, Object> map = objectMapper.readValue(httpResponse.getEntity().getContent(), Map.class);
            Integer totalCount = (Integer) map.get("total_count");
            Boolean incompleteResults = (Boolean) map.get("incomplete_results");
            List<Map<String, Object>> items = (List<Map<String, Object>>) map.get("items");

            userDetailsDTO.setTotalCount(totalCount);
            userDetailsDTO.setIncompleteResults(incompleteResults);

            List<ItemsDTO> itemsDTOList = new ArrayList<>();
            for (Map<String, Object> item : items) {

                ItemsDTO itemsDTO = new ItemsDTO();

                itemsDTO.setLogin(String.valueOf(item.get("login")));
                itemsDTO.setId((Integer)item.get("id"));
                itemsDTO.setAvatarUrl(String.valueOf(item.get("avatar_url")));
                itemsDTO.setGravatarId(String.valueOf(item.get("gravatar_id")));
                itemsDTO.setFollowersUrl(String.valueOf(item.get("followers_url")));
                itemsDTO.setNodeId(String.valueOf(item.get("node_id")));
                itemsDTO.setUrl(String.valueOf(item.get("url")));
                itemsDTO.setHtmlUrl(String.valueOf(item.get("html_url")));
                itemsDTO.setFollowingUrl(String.valueOf(item.get("following_url")));
                itemsDTO.setGistsUrl(String.valueOf(item.get("gists_url")));
                itemsDTO.setStarredUrl(String.valueOf(item.get("starred_url")));
                itemsDTO.setSubscriptionsUrl(String.valueOf(item.get("subscriptions_url")));
                itemsDTO.setOrganizationsUrl(String.valueOf(item.get("organizations_url")));
                itemsDTO.setReposUrl(String.valueOf(item.get("repos_url")));
                itemsDTO.setEventsUrl(String.valueOf(item.get("events_url")));
                itemsDTO.setReceivedEventsUrl(String.valueOf(item.get("received_events_url")));
                itemsDTO.setType(String.valueOf(item.get("type")));
                itemsDTO.setSiteAdmin(Boolean.valueOf("false"));

                itemsDTOList.add(itemsDTO);
            }
            userDetailsDTO.setItems(itemsDTOList);


        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return userDetailsDTO;
    }
}
