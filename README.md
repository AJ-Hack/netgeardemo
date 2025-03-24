# Clone & enter project
git clone [your-repo-url] netgeardemo
cd netgeardemo

# Start MySQL in Docker
docker-compose up -d mysql

# Run the app
./gradlew bootRun